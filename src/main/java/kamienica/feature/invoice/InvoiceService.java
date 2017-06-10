package kamienica.feature.invoice;

import kamienica.core.calculator.EnergyConsumptionCalculator;
import kamienica.core.calculator.GasConsumptionCalculator;
import kamienica.core.calculator.PaymentCalculator;
import kamienica.core.calculator.WaterConsumptionCalculator;
import kamienica.core.util.SecurityDetails;
import kamienica.feature.division.IDivisionService;
import kamienica.feature.meter.IMeterService;
import kamienica.feature.payment.IPaymentDao;
import kamienica.feature.reading.IReadingDao;
import kamienica.feature.reading.IReadingService;
import kamienica.feature.readingdetails.ReadingDetailsDao;
import kamienica.feature.settings.ISettingsDao;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import kamienica.model.enums.Resolvement;
import kamienica.model.enums.WaterHeatingSystem;
import org.hibernate.criterion.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceService implements IInvoiceService {

    private final IDivisionService divisionService;
    private final IReadingService readingService;
    private final IMeterService meterService;
    private final IInvoiceDao invoiceDao;
    private final IReadingDao readingDao;
    private final IPaymentDao paymentDao;
    private final ISettingsDao settingsDao;
    private final ReadingDetailsDao readingDetailsDao;

    @Autowired
    public InvoiceService(IDivisionService divisionService, IReadingService readingService,
                          IMeterService meterService, IInvoiceDao invoiceDao,
                          IReadingDao readingDao, IPaymentDao paymentDao,
                          ISettingsDao settingsDao, ReadingDetailsDao readingDetailsDao) {
        this.divisionService = divisionService;
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
    public void save(final Invoice invoice) {
        final Media media = invoice.getMedia();
        final List<Division> division = divisionService.createDivisionForResidence(invoice);

        switch (media) {
            case ENERGY:
                saveEnergy(invoice, division);
                break;
            case GAS:
                saveGas(invoice, division);
                break;
            case WATER:
                saveWater(invoice, division);
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
        invoiceDao.delete(id);
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


    private void saveWater(Invoice invoice, List<Division> division) {
        final Residence r = invoice.getResidence();
        final List<Tenant> tenants = extractTenantsFromDivision(division);
        final List<Apartment> apartments = extractApartmentsFromDivision(division);
        List<Reading> ReadingOld = readingService.getPreviousReading(
                invoice.getReadingDetails().getReadingDate(), meterService.list(r, Media.WATER));

        List<Reading> ReadingNew = readingService
                .getByDate(invoice.getResidence(), invoice.getReadingDetails().getReadingDate(), Media.WATER);

        List<MediaUsage> usageWater = WaterConsumptionCalculator.countConsumption(apartments, ReadingOld,
                ReadingNew);
        List<Payment> payments = PaymentCalculator.createPaymentList(tenants, invoice,
                division, usageWater);

        saveAllInvoiceRelatedData(invoice, payments);
    }

    private void saveGas(Invoice invoice, List<Division> division) {
        Settings settings = settingsDao.getList().get(0);
        final Residence r = invoice.getResidence();
        final List<Tenant> tenants = extractTenantsFromDivision(division);
        final List<Apartment> apartments = extractApartmentsFromDivision(division);
        List<Reading> ReadingOld = readingService.getPreviousReading(invoice.getReadingDetails().getReadingDate(),
                meterService.list(r, Media.GAS));
        List<Reading> ReadingNew = readingService.getByDate(invoice.getResidence(), invoice.getReadingDetails().getReadingDate(),
                Media.GAS);
        List<MediaUsage> usageGas;
        if (settings.getWaterHeatingSystem().equals(WaterHeatingSystem.SHARED_GAS)) {
            ReadingDetails readingDetailsNew = readingDetailsDao.getLatestPriorToDate(invoice.getReadingDetails().getReadingDate(), invoice.getResidence(), Media.WATER);
            ReadingDetails readingDetailsOld = readingDetailsDao.getLatestPriorToDate(readingDetailsNew.getReadingDate(), invoice.getResidence(), Media.WATER);
            List<Reading> waterNew = readingDao.findByCriteria(Restrictions.eq("readingDetails", readingDetailsNew));
            List<Reading> waterOld = readingDao.findByCriteria(Restrictions.eq("readingDetails", readingDetailsOld));

            usageGas = GasConsumptionCalculator.countConsumption(apartments, ReadingOld, ReadingNew, waterOld,
                    waterNew);
        } else {
            usageGas = GasConsumptionCalculator.countConsumption(apartments, ReadingOld, ReadingNew);
        }

        List<Payment> payments = PaymentCalculator.createPaymentList(tenants, invoice,
                division, usageGas);

        saveAllInvoiceRelatedData(invoice, payments);
    }

    private void saveEnergy(Invoice invoice, List<Division> division) {
        final LocalDate baseReadingDate = invoice.getReadingDetails().getReadingDate();
        final Residence r = invoice.getResidence();
        final List<Tenant> tenants = extractTenantsFromDivision(division);
        final List<Apartment> apartments = extractApartmentsFromDivision(division);

        List<Reading> ReadingOld = readingService.getPreviousReading(baseReadingDate, meterService.list(r, Media.ENERGY));
        List<Reading> ReadingNew = readingService.getByDate(invoice.getResidence(), baseReadingDate, Media.ENERGY);

        List<MediaUsage> usageEnergy = EnergyConsumptionCalculator.countConsumption(apartments, ReadingOld,
                ReadingNew);
        List<Payment> payments = PaymentCalculator.createPaymentList(tenants, invoice, division, usageEnergy);

        saveAllInvoiceRelatedData(invoice, payments);
    }

    private List<Tenant> extractTenantsFromDivision(List<Division> division) {
        return division.stream().map(Division::getTenant).distinct().collect(Collectors.toList());
    }

    private List<Apartment> extractApartmentsFromDivision(List<Division> division) {
        return division.stream().map(Division::getApartment).distinct().collect(Collectors.toList());
    }

    private void saveAllInvoiceRelatedData(Invoice invoice, List<Payment> payments) {
        invoiceDao.save(invoice);
        ReadingDetails rd = invoice.getReadingDetails();
        rd.setResolvement(Resolvement.RESOLVED);
        readingDetailsDao.update(rd);

        for (Payment payment : payments) {
            paymentDao.save(payment);
        }
    }

}
