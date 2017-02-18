package kamienica.feature.invoice;

import kamienica.core.calculator.EnergyConsumptionCalculator;
import kamienica.core.calculator.GasConsumptionCalculator;
import kamienica.core.calculator.PaymentCalculator;
import kamienica.core.calculator.WaterConsumptionCalculator;
import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.IApartmentDao;
import kamienica.feature.division.IDivisionService;
import kamienica.feature.meter.IMeterService;
import kamienica.feature.payment.IPaymentDao;
import kamienica.feature.reading.IReadingDao;
import kamienica.feature.reading.IReadingService;
import kamienica.feature.settings.ISettingsDao;
import kamienica.feature.tenant.ITenantDao;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import kamienica.model.enums.Status;
import kamienica.model.enums.WaterHeatingSystem;
import kamienica.model.exception.InvalidDivisionException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class InvoiceService implements IInvoiceService {

    private final ITenantDao tenantDao;
    private final IDivisionService divisionService;
    private final IApartmentDao apartmentDao;
    private final IReadingService readingService;
    private final IMeterService meterService;
    private final IInvoiceDao invoiceDao;
    private final IReadingDao readingDao;
    private final IPaymentDao paymentDao;
    private final ISettingsDao settingsDao;

    @Autowired
    public InvoiceService(ITenantDao tenantDao, IDivisionService divisionService,
                          IApartmentDao apartmentDao, IReadingService readingService,
                          IMeterService meterService, IInvoiceDao invoiceDao,
                          IReadingDao readingDao, IPaymentDao paymentDao,
                          ISettingsDao settingsDao) {
        this.tenantDao = tenantDao;
        this.divisionService = divisionService;
        this.apartmentDao = apartmentDao;
        this.readingService = readingService;
        this.meterService = meterService;
        this.invoiceDao = invoiceDao;
        this.readingDao = readingDao;
        this.paymentDao = paymentDao;
        this.settingsDao = settingsDao;
    }


    @SuppressWarnings("unchecked")
    @Override
    public void save(final Invoice invoice, final Media media, final Tenant tenant, final Residence residence) {
        final List<Apartment> apartments = apartmentDao.findByCriteria(Restrictions.eq("residence", residence));
        final List<Tenant> tenants = tenantDao.findByCriteria(Restrictions.in("apartment", apartments), Restrictions.eq("status", Status.ACTIVE));
        final List<Division> division = divisionService.createDivisionForResidence(residence);
        invoice.setResidence(residence);

        switch (media) {
            case ENERGY:
                saveEnergy(invoice, apartments, tenants, division);
                break;
            case GAS:
                saveGas(invoice, apartments, tenants, division);
                break;
            case WATER:
                saveWater(invoice, apartments, tenants, division);
                break;
            default:
                break;
        }

    }

    @Override
    public void delete(Long id) {
        Invoice invoice;
        invoice = invoiceDao.getById(id);
        paymentDao.deleteForInvoice(invoice);
        readingDao.changeResolvementState(invoice, false);
        invoiceDao.deleteById(id);
    }

    @Override
    public Invoice getByID(Long id) {
        return invoiceDao.getById(id);
    }

    @Override
    public List<Invoice> list(final Media media) {
        final List<Residence> residences = SecurityDetails.getResidencesForOwner();
        final Criterion forTheseResidences = Restrictions.in("residence", residences);
        return invoiceDao.findByCriteria(forTheseResidences);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Reading> getUnpaidReadingForNewIncvoice(final Residence r, final Media media) throws InvalidDivisionException {

        if (!settingsDao.isDivisionCorrect())

        {
            throw new InvalidDivisionException(
                    "Lista aktualnych najemców i mieszkań się nie zgadza. Sprawdź algorytm podziału");
        }
        return readingDao.getUnresolvedReadings(media, r);


    }

    @Override
    public void list(final Residence r, Map<String, Object> model, final Media media) {
        //TODO get rid of that ugly thing
        model.put("invoice", invoiceDao.getList(r, media));
        switch (media) {
            case GAS:
                model.put("editlUrl", "/Admin/Invoice/invoiceGasEdit.html?id=");
                model.put("delUrl", "/Admin/Invoice/invoiceGasDelete.html?id=");
                model.put("media", "Gaz");
                break;
            case WATER:
                model.put("editlUrl", "/Admin/Invoice/invoiceWaterEdit.html?id=");
                model.put("delUrl", "/Admin/Invoice/invoiceWaterDelete.html?id=");
                model.put("media", "Woda");
                break;
            case ENERGY:
                model.put("editlUrl", "/Admin/Invoice/invoiceEnergyEdit.html?id=");
                model.put("delUrl", "/Admin/Invoice/invoiceEnergyDelete.html?id=");
                model.put("media", "Energia");
                break;
            default:
                break;
        }

    }


    private void saveWater(Invoice invoice, List<Apartment> apartments, List<Tenant> tenants, List<Division> division) {
        List<Reading> ReadingOld = readingService.getPreviousReading(
                invoice.getReadingDetails().getReadingDate(), meterService.getIdList(Media.WATER));

        List<Reading> ReadingNew = readingService
                .getByDate(invoice.getResidence(), invoice.getReadingDetails().getReadingDate(), Media.WATER);

        List<MediaUsage> usageWater = WaterConsumptionCalculator.countConsumption(apartments, ReadingOld,
                ReadingNew);
        List<Payment> paymentWater = PaymentCalculator.createPaymentList(tenants, invoice,
                division, usageWater);

        invoiceDao.save(invoice);
        readingDao.changeResolvementState(invoice, true);
        for (Payment payment : paymentWater) {
            paymentDao.save(payment);
        }
    }

    private void saveGas(Invoice invoice, List<Apartment> apartments, List<Tenant> tenants, List<Division> division) {
        Settings settings = settingsDao.getList().get(0);

        List<Reading> ReadingOld = readingService.getPreviousReading(invoice.getReadingDetails().getReadingDate(),
                meterService.getIdList(Media.GAS));
        List<Reading> ReadingNew = readingService.getByDate(invoice.getResidence(), invoice.getReadingDetails().getReadingDate(),
                Media.GAS);
        List<MediaUsage> usageGas;
        if (settings.getWaterHeatingSystem().equals(WaterHeatingSystem.SHARED_GAS)) {

            List<Reading> waterNew = readingDao
                    .getWaterReadingForGasConsumption(invoice.getResidence(), invoice.getReadingDetails().getReadingDate());

            List<Reading> waterOld = readingDao
                    .getWaterReadingForGasConsumption(invoice.getResidence(), waterNew.get(0).getReadingDetails().getReadingDate());

            usageGas = GasConsumptionCalculator.countConsumption(apartments, ReadingOld, ReadingNew, waterOld,
                    waterNew);
        } else {
            usageGas = GasConsumptionCalculator.countConsumption(apartments, ReadingOld, ReadingNew);
        }

        List<Payment> paymentGas = PaymentCalculator.createPaymentList(tenants, invoice,
                division, usageGas);

        invoiceDao.save(invoice);
        readingDao.changeResolvementState(invoice, true);

        for (Payment payment : paymentGas) {
            paymentDao.save(payment);
        }
    }

    private <T extends Invoice> void saveEnergy(T invoice, List<Apartment> apartments, List<Tenant> tenants, List<Division> division) {
        final LocalDate baseReadingDate = invoice.getReadingDetails().getReadingDate();


        List<Reading> ReadingOld = readingService.getPreviousReading(baseReadingDate, meterService.getIdList(Media.ENERGY));

        List<Reading> ReadingNew = readingService.getByDate(invoice.getResidence(), baseReadingDate, Media.ENERGY);

        List<MediaUsage> usageEnergy = EnergyConsumptionCalculator.countConsumption(apartments, ReadingOld,
                ReadingNew);
        List<Payment> paymentEnergy = PaymentCalculator.createPaymentList(tenants, invoice, division, usageEnergy);

        invoiceDao.save(invoice);
        readingDao.changeResolvementState(invoice, true);
        for (Payment payment : paymentEnergy) {
            paymentDao.save(payment);
        }
    }

}
