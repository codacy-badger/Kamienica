package kamienica.feature.invoice;

import kamienica.core.calculator.EnergyConsumptionCalculator;
import kamienica.core.calculator.GasConsumptionCalculator;
import kamienica.core.calculator.PaymentCalculator;
import kamienica.core.calculator.WaterConsumptionCalculator;
import kamienica.core.enums.Media;
import kamienica.core.enums.Status;
import kamienica.core.enums.WaterHeatingSystem;
import kamienica.core.exception.InvalidDivisionException;
import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.ApartmentDao;
import kamienica.feature.division.DivisionDao;
import kamienica.feature.division.DivisionService;
import kamienica.feature.meter.MeterService;
import kamienica.feature.payment.PaymentDao;
import kamienica.feature.reading.ReadingEnergyDao;
import kamienica.feature.reading.ReadingGasDao;
import kamienica.feature.reading.ReadingService;
import kamienica.feature.reading.ReadingWaterDao;
import kamienica.feature.residence.ResidenceService;
import kamienica.feature.settings.SettingsDao;
import kamienica.feature.tenant.TenantDao;
import kamienica.model.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private TenantDao tenantDao;
    @Autowired
    private DivisionDao divisionDao;
    @Autowired
    private DivisionService divisionService;
    @Autowired
    private ApartmentDao apartmentDao;
    @Autowired
    private ReadingService readingService;
    @Autowired
    private MeterService meterService;
    @Autowired
    private InvoiceAbstractDao<InvoiceEnergy> invoiceEnergyDao;
    @Autowired
    private InvoiceAbstractDao<InvoiceGas> invoiceGasDao;
    @Autowired
    private InvoiceAbstractDao<InvoiceWater> invoiceWaterDao;
    @Autowired
    private ReadingEnergyDao readingEnergyDao;
    @Autowired
    private ReadingGasDao readingGasDao;
    @Autowired
    private ReadingWaterDao readingWaterDao;
    @Autowired
    private PaymentDao<PaymentGas> paymentGasDao;
    @Autowired
    private PaymentDao<PaymentEnergy> paymentEnergyDao;
    @Autowired
    private PaymentDao<PaymentWater> paymentWaterDao;
    @Autowired
    private SettingsDao settingsDao;
    @Autowired
    private ResidenceService residenceService;


    @SuppressWarnings("unchecked")
    @Override
    public <T extends Invoice> void save(final T invoice, final Media media, final Tenant tenant, final Residence residence) throws InvalidDivisionException {
        final List<Apartment> apartments = apartmentDao.findByCriteria(Restrictions.eq("residence", residence));
        final List<Tenant> tenants = tenantDao.findByCriteria(Restrictions.in("apartment", apartments), Restrictions.eq("status", Status.ACTIVE));
        final List<Division> division = divisionService.createDivisionForResidence(residence);
        invoice.setResidence(residence);

        switch (media) {
            case ENERGY:
                List<ReadingEnergy> readingEnergyOld = readingService.getPreviousReadingEnergy(
                        invoice.getBaseReading().getReadingDate(), meterService.getIdList(Media.ENERGY));

                List<ReadingEnergy> readingEnergyNew = (List<ReadingEnergy>) readingService
                        .getByDate(invoice.getBaseReading().getReadingDate(), Media.ENERGY);

                List<MediaUsage> usageEnergy = EnergyConsumptionCalculator.countConsumption(apartments, readingEnergyOld,
                        readingEnergyNew);
                List<PaymentEnergy> paymentEnergy = PaymentCalculator.createPaymentEnergyList(tenants,
                        (InvoiceEnergy) invoice, division, usageEnergy);

                invoiceEnergyDao.save((InvoiceEnergy) invoice);
                readingEnergyDao.changeResolvementState(invoice, true);
                for (PaymentEnergy payment : paymentEnergy) {
                    paymentEnergyDao.save(payment);
                }
                break;

            case GAS:
                Settings settings = settingsDao.getList().get(0);

                List<ReadingGas> readingGasOld = readingService.getPreviousReadingGas(invoice.getReadingDate(),
                        meterService.getIdList(Media.GAS));
                List<ReadingGas> readingGasNew = (List<ReadingGas>) readingService.getByDate(invoice.getReadingDate(),
                        Media.GAS);
                List<MediaUsage> usageGas;
                if (settings.getWaterHeatingSystem().equals(WaterHeatingSystem.SHARED_GAS)) {
                    List<ReadingWater> waterNew = readingWaterDao
                            .getWaterReadingForGasConsumption2(invoice.getReadingDate());

                    List<ReadingWater> waterOld = readingWaterDao
                            .getWaterReadingForGasConsumption2(waterNew.get(0).getReadingDate());

                    usageGas = GasConsumptionCalculator.countConsumption(apartments, readingGasOld, readingGasNew, waterOld,
                            waterNew);
                } else {
                    usageGas = GasConsumptionCalculator.countConsumption(apartments, readingGasOld, readingGasNew);
                }

                List<PaymentGas> paymentGas = PaymentCalculator.createPaymentGasList(tenants, (InvoiceGas) invoice,
                        division, usageGas);

                invoiceGasDao.save((InvoiceGas) invoice);
                readingGasDao.changeResolvementState(invoice, true);

                for (PaymentGas payment : paymentGas) {
                    paymentGasDao.save(payment);
                }

                break;

            case WATER:
                List<ReadingWater> readingWaterOld = readingService.getPreviousReadingWater(
                        invoice.getBaseReading().getReadingDate(), meterService.getIdList(Media.WATER));

                List<ReadingWater> readingWaterNew = (List<ReadingWater>) readingService
                        .getByDate(invoice.getBaseReading().getReadingDate(), Media.WATER);

                List<MediaUsage> usageWater = WaterConsumptionCalculator.countConsumption(apartments, readingWaterOld,
                        readingWaterNew);
                List<PaymentWater> paymentWater = PaymentCalculator.createPaymentWaterList(tenants, (InvoiceWater) invoice,
                        division, usageWater);

                invoiceWaterDao.save((InvoiceWater) invoice);
                readingWaterDao.changeResolvementState(invoice, true);
                for (PaymentWater payment : paymentWater) {
                    paymentWaterDao.save(payment);
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void delete(Long id, Media media) {
        Invoice invoice;
        switch (media) {
            case ENERGY:
                invoice = invoiceEnergyDao.getById(id);
                paymentEnergyDao.deleteForInvoice(invoice);
                readingEnergyDao.changeResolvementState(invoice, false);
                invoiceEnergyDao.deleteById(id);
                break;
            case GAS:
                invoice = invoiceGasDao.getById(id);
                paymentGasDao.deleteForInvoice(invoice);
                readingGasDao.changeResolvementState(invoice, false);
                invoiceGasDao.deleteById(id);
                break;
            case WATER:
                invoice = invoiceWaterDao.getById(id);
                paymentWaterDao.deleteForInvoice(invoice);
                readingWaterDao.changeResolvementState(invoice, false);
                invoiceWaterDao.deleteById(id);

                break;
            default:
                break;
        }
    }

    @Override
    public InvoiceEnergy getEnergyByID(Long id) {
        return invoiceEnergyDao.getById(id);
    }


    @Override
    public InvoiceGas getGasByID(Long id) {
        return invoiceGasDao.getById(id);
    }

    @Override
    public InvoiceWater getWaterByID(Long id) {
        return invoiceWaterDao.getById(id);
    }

    @Override
    public List<? extends Invoice> getList(final Media media) {
        final List<Residence> residences = SecurityDetails.getResidencesForOwner();
        final Criterion forTheseResidences = Restrictions.in("residence", residences);
        switch (media) {
            case ENERGY:
                return invoiceEnergyDao.findByCriteria(forTheseResidences);
            case GAS:
                return invoiceGasDao.findByCriteria(forTheseResidences);
            case WATER:
                return invoiceWaterDao.findByCriteria(forTheseResidences);
            default:
                return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Reading> List<T> getUnpaidReadingForNewIncvoice(Media media) throws InvalidDivisionException {

        if (!settingsDao.isDivisionCorrect())

        {
            throw new InvalidDivisionException(
                    "Lista aktualnych najemców i mieszkań się nie zgadza. Sprawdź algorytm podziału");
        }
        switch (media) {
            case ENERGY:
                return (List<T>) readingEnergyDao.getUnresolvedReadings();

            case GAS:

                return (List<T>) readingGasDao.getUnresolvedReadings();

            case WATER:

                return (List<T>) readingWaterDao.getUnresolvedReadings();

            default:
                return null;
        }

    }

    @Override
    public void list(Map<String, Object> model, Media media) {
        //TODO get rid of that ugly thing
        switch (media) {
            case GAS:
                model.put("invoice", invoiceEnergyDao.getList());
                model.put("editlUrl", "/Admin/Invoice/invoiceGasEdit.html?id=");
                model.put("delUrl", "/Admin/Invoice/invoiceGasDelete.html?id=");
                model.put("media", "Gaz");
                break;
            case WATER:
                model.put("invoice", invoiceGasDao.getList());
                model.put("editlUrl", "/Admin/Invoice/invoiceWaterEdit.html?id=");
                model.put("delUrl", "/Admin/Invoice/invoiceWaterDelete.html?id=");
                model.put("media", "Woda");
                break;
            case ENERGY:
                model.put("invoice", invoiceWaterDao.getList());
                model.put("editlUrl", "/Admin/Invoice/invoiceEnergyEdit.html?id=");
                model.put("delUrl", "/Admin/Invoice/invoiceEnergyDelete.html?id=");
                model.put("media", "Energia");
                break;
            default:
                break;
        }

    }

}
