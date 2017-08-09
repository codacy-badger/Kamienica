package kamienica.feature.payment;

import kamienica.core.calculator.GasConsumptionCalculator;
import kamienica.core.calculator.IConsumptionCalculator;
import kamienica.core.calculator.PaymentCalculator;
import kamienica.core.calculator.StandardUsageCalculator;
import kamienica.feature.division.IDivisionService;
import kamienica.feature.meter.IMeterService;
import kamienica.feature.reading.IReadingDao;
import kamienica.feature.reading.IReadingService;
import kamienica.feature.readingdetails.IReadingDetailsDao;
import kamienica.feature.settings.ISettingsService;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import kamienica.model.enums.WaterHeatingSystem;
import kamienica.model.exception.NegativeConsumptionValue;
import kamienica.model.exception.UsageCalculationException;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentService implements IPaymentService {

    private final IPaymentDao paymentDao;
    private final IReadingService readingService;
    private final IMeterService meterService;
    private final IDivisionService divisionService;
    private final ISettingsService settingsService;
    private final IReadingDetailsDao readingDetailsDao;
    private final IReadingDao readingDao;

    @Autowired
    public PaymentService(final IPaymentDao paymentDao, IReadingService readingService, IMeterService meterService, IDivisionService divisionService, ISettingsService settingsService, IReadingDetailsDao readingDetailsDao, IReadingDao readingDao) {
        this.paymentDao = paymentDao;
        this.readingService = readingService;
        this.meterService = meterService;
        this.divisionService = divisionService;
        this.settingsService = settingsService;
        this.readingDetailsDao = readingDetailsDao;
        this.readingDao = readingDao;
    }

    @Override
    public List<Payment> getPaymentList(final Media media) {
        return paymentDao.getList(media);
    }

    @Override
    public List<Payment> getPaymentForTenant(final Tenant tenant, final Media media) {
        return paymentDao.getPaymentForTenant(tenant, media);
    }


    @Override
    public void savePayments(final Invoice invoice) throws UsageCalculationException, NegativeConsumptionValue {
        if(invoice.getMedia().equals(Media.GAS)) {
            saveGas(invoice);
        } else {
            saveDefault(invoice);
        }
    }

    private void saveGas(Invoice invoice) throws UsageCalculationException, NegativeConsumptionValue {
        Settings settings = settingsService.getSettings(invoice.getResidence());
        final Residence r = invoice.getResidence();
        List<Division> division = divisionService.createDivisionForResidence(invoice);
        final List<Apartment> apartments = extractApartmentsFromDivision(division);
        List<Reading> ReadingOld = readingService.getPreviousReading(invoice.getReadingDetails().getReadingDate(),
                meterService.list(r, Media.GAS));
        List<Reading> ReadingNew = readingService.getForInvoice(invoice);
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

        List<Payment> payments = PaymentCalculator.createPaymentList(invoice,
                division, usageGas);

        for (Payment p : payments) {
            paymentDao.save(p);
        }
    }

    private void saveDefault(final Invoice invoice) throws UsageCalculationException, NegativeConsumptionValue {
    final LocalDate baseReadingDate = invoice.getReadingDetails().getReadingDate();
    final Residence r = invoice.getResidence();
    final List<Division> division = divisionService.createDivisionForResidence(invoice);
    final List<Apartment> apartments = extractApartmentsFromDivision(division);
    List<Reading> readings = new ArrayList<>();
    readings.addAll(readingService.getPreviousReading(baseReadingDate, meterService.list(r, invoice.getMedia())));
    readings.addAll(readingService.getForInvoice(invoice));

    IConsumptionCalculator calculator = new StandardUsageCalculator();
    List<MediaUsage> usage = calculator.calculateConsumption(apartments, readings);
    List<Payment> payments = PaymentCalculator.createPaymentList(invoice, division, usage);

    for (Payment p : payments) {
        paymentDao.save(p);
    }
}
    private List<Apartment> extractApartmentsFromDivision(List<Division> division) {
        return division.stream().map(Division::getApartment).distinct().collect(Collectors.toList());
    }
}
