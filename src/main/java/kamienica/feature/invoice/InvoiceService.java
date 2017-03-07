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
import kamienica.feature.readingdetails.ReadingDetailsDao;
import kamienica.feature.settings.ISettingsDao;
import kamienica.feature.tenant.ITenantDao;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import kamienica.model.enums.Resolvement;
import kamienica.model.enums.Status;
import kamienica.model.enums.WaterHeatingSystem;
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
    private final ReadingDetailsDao readingDetailsDao;

    @Autowired
    public InvoiceService(ITenantDao tenantDao, IDivisionService divisionService,
                          IApartmentDao apartmentDao, IReadingService readingService,
                          IMeterService meterService, IInvoiceDao invoiceDao,
                          IReadingDao readingDao, IPaymentDao paymentDao,
                          ISettingsDao settingsDao, ReadingDetailsDao readingDetailsDao) {
        this.tenantDao = tenantDao;
        this.divisionService = divisionService;
        this.apartmentDao = apartmentDao;
        this.readingService = readingService;
        this.meterService = meterService;
        this.invoiceDao = invoiceDao;
        this.readingDao = readingDao;
        this.paymentDao = paymentDao;
        this.settingsDao = settingsDao;
        this.readingDetailsDao = readingDetailsDao;
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
        final Invoice invoice = invoiceDao.getById(id);
        final ReadingDetails details = invoice.getReadingDetails();
        details.setResolvement(Resolvement.UNRESOLVED);

        readingDetailsDao.update(details);
        paymentDao.deleteForInvoice(invoice);
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
        final Criterion forMedia = Restrictions.eq("media", media);
        return invoiceDao.findByCriteria(forTheseResidences, forMedia);
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
        final Residence r = apartments.get(0).getResidence();
        List<Reading> ReadingOld = readingService.getPreviousReading(
                invoice.getReadingDetails().getReadingDate(), meterService.list(r, Media.WATER));

        List<Reading> ReadingNew = readingService
                .getByDate(invoice.getResidence(), invoice.getReadingDetails().getReadingDate(), Media.WATER);

        List<MediaUsage> usageWater = WaterConsumptionCalculator.countConsumption(apartments, ReadingOld,
                ReadingNew);
        List<Payment> paymentWater = PaymentCalculator.createPaymentList(tenants, invoice,
                division, usageWater);

        invoiceDao.save(invoice);
        ReadingDetails rd = invoice.getReadingDetails();
        rd.setResolvement(Resolvement.RESOLVED);
        readingDetailsDao.update(rd);
        for (Payment payment : paymentWater) {
            paymentDao.save(payment);
        }
    }

    private void saveGas(Invoice invoice, List<Apartment> apartments, List<Tenant> tenants, List<Division> division) {
        Settings settings = settingsDao.getList().get(0);
        final Residence r = apartments.get(0).getResidence();
        List<Reading> ReadingOld = readingService.getPreviousReading(invoice.getReadingDetails().getReadingDate(),
                meterService.list(r, Media.GAS));
        List<Reading> ReadingNew = readingService.getByDate(invoice.getResidence(), invoice.getReadingDetails().getReadingDate(),
                Media.GAS);
        List<MediaUsage> usageGas;
        if (settings.getWaterHeatingSystem().equals(WaterHeatingSystem.SHARED_GAS)) {
            ReadingDetails readingDetails = readingDetailsDao.getLatestPriorToDate(invoice.getReadingDetails().getReadingDate(), invoice.getResidence(), Media.WATER);
            List<Reading> waterNew = readingDao
                    .getWaterReadingForGasConsumption(invoice.getResidence(), invoice.getReadingDetails());

            List<Reading> waterOld = readingDao.findByCriteria(Restrictions.eq("readingDetails", readingDetails));
                   // .getWaterReadingForGasConsumption(invoice.getResidence(), waterNew.get(0).getReadingDetails().getReadingDate());

            usageGas = GasConsumptionCalculator.countConsumption(apartments, ReadingOld, ReadingNew, waterOld,
                    waterNew);
        } else {
            usageGas = GasConsumptionCalculator.countConsumption(apartments, ReadingOld, ReadingNew);
        }

        List<Payment> paymentGas = PaymentCalculator.createPaymentList(tenants, invoice,
                division, usageGas);

        invoiceDao.save(invoice);
        ReadingDetails rd = invoice.getReadingDetails();
        rd.setResolvement(Resolvement.RESOLVED);
        readingDetailsDao.update(rd);

        for (Payment payment : paymentGas) {
            paymentDao.save(payment);
        }
    }

    private void saveEnergy(Invoice invoice, List<Apartment> apartments, List<Tenant> tenants, List<Division> division) {
        final LocalDate baseReadingDate = invoice.getReadingDetails().getReadingDate();
        final Residence r = apartments.get(0).getResidence();

        List<Reading> ReadingOld = readingService.getPreviousReading(baseReadingDate, meterService.list(r, Media.ENERGY));
        List<Reading> ReadingNew = readingService.getByDate(invoice.getResidence(), baseReadingDate, Media.ENERGY);

        List<MediaUsage> usageEnergy = EnergyConsumptionCalculator.countConsumption(apartments, ReadingOld,
                ReadingNew);
        List<Payment> paymentEnergy = PaymentCalculator.createPaymentList(tenants, invoice, division, usageEnergy);

        invoiceDao.save(invoice);
        ReadingDetails rd = invoice.getReadingDetails();
        rd.setResolvement(Resolvement.RESOLVED);
        readingDetailsDao.update(rd);

        for (Payment payment : paymentEnergy) {
            paymentDao.save(payment);
        }
    }

}
