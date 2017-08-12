package kamienica.feature.payment.calculator;

import kamienica.core.util.CommonUtils;
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
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentCalculator implements IPaymentCalculator{

    private final ISettingsService settingsService;
    private final IDivisionService divisionService;
    private final IReadingService readingService;
    private final IMeterService meterService;
    private final IReadingDetailsDao readingDetailsDao;
    private final IReadingDao readingDao;

    @Autowired
    public PaymentCalculator(ISettingsService settingsService, IDivisionService divisionService, IReadingService readingService,
                             IMeterService meterService, IReadingDetailsDao readingDetailsDao, IReadingDao readingDao) {
        this.settingsService = settingsService;
        this.divisionService = divisionService;
        this.readingService = readingService;
        this.meterService = meterService;
        this.readingDetailsDao = readingDetailsDao;
        this.readingDao = readingDao;
    }

    @Override
    public List<Payment> createPaymentList(final Invoice invoice) throws UsageCalculationException, NegativeConsumptionValue {

        if(invoice.getMedia().equals(Media.GAS)) {
            return createGasPayments(invoice);
        }

        final LocalDate baseReadingDate = invoice.getReadingDetails().getReadingDate();
        final Residence r = invoice.getResidence();
        final List<Division> division = divisionService.createDivisionForResidence(invoice);
        final List<Apartment> apartments = extractApartmentsFromDivision(division);
        final List<Reading> readings = new ArrayList<>();
        readings.addAll(readingService.getPreviousReading(baseReadingDate, meterService.list(r, invoice.getMedia())));
        readings.addAll(readingService.getForInvoice(invoice));

        final IConsumptionCalculator calculator = createCalculator(invoice);

        final List<MediaUsage> usage = calculator.calculateConsumption(apartments, readings);

        return generatePayments(invoice, division, usage);
    }


    private List<Payment> createGasPayments(final Invoice invoice) {
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

        return generatePayments(invoice, division, usageGas);
    }


    private IConsumptionCalculator createCalculator(final Invoice invoice) {
        final Settings settings = settingsService.getSettings(invoice.getResidence());
        return UsageCalculatorProvider.provideCalculator(settings.getWaterHeatingSystem(), invoice.getMedia());
    }

    private List<Payment> generatePayments(final Invoice invoice,
                                           final List<Division> division, final List<MediaUsage> usage) {
        double sumOfExpences = invoice.getTotalAmount();
        List<Payment> listToReturn = new ArrayList<>();

        double usageSum = sumUsage(usage);


        final List<Tenant> tenants = extractTenantsFromDivision(division);
        for (Tenant tenant : tenants) {
            HashMap<Integer, Double> divisionForTenant = setTenantDivision(division, tenant);
            double payment = 0;

            for (MediaUsage w : usage) {
                double factor = w.getUsage() / usageSum;
                payment += sumOfExpences * factor * divisionForTenant.get(w.getApartment().getApartmentNumber());
            }

            payment = CommonUtils.decimalFormat(payment);
            Payment forList = new Payment();
            forList.setInvoice(invoice);
            forList.setTenant(tenant);
            forList.setPaymentAmount(payment);
            forList.setPaymentDate(new LocalDate());
            listToReturn.add(forList);
        }

        return listToReturn;
    }

    private List<Apartment> extractApartmentsFromDivision(final List<Division> division) {
        return division.stream().map(Division::getApartment).distinct().collect(Collectors.toList());
    }

    private double sumUsage(List<MediaUsage> listaZuzycia) {
        double suma = 0;
        for (MediaUsage i : listaZuzycia) {
            suma += i.getUsage();
        }

        return suma;
    }

    private HashMap<Integer, Double> setTenantDivision(List<Division> division, Tenant tenant) {
        HashMap<Integer, Double> output = new HashMap<>();
        division.stream().filter(p -> tenant.getId().equals(p.getTenant().getId())).forEachOrdered(p -> {
            output.put(p.getApartment().getApartmentNumber(), p.getDivisionValue());
        });
        return output;
    }

    private List<Tenant> extractTenantsFromDivision(List<Division> division) {
        return division.stream().map(Division::getTenant).distinct().collect(Collectors.toList());
    }
}
