package kamienica.feature.payment.calculator;

import kamienica.core.util.CommonUtils;
import kamienica.feature.division.IDivisionService;
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
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentCalculator implements IPaymentCalculator{

    private final ISettingsService settingsService;
    private final IDivisionService divisionService;
    private final Map<String, IConsumptionCalculator> consumptionCalculatorMap;

    @Autowired
    public PaymentCalculator(ISettingsService settingsService, IDivisionService divisionService,
                             Map<String, IConsumptionCalculator> consumptionCalculatorMap) {
        this.settingsService = settingsService;
        this.divisionService = divisionService;
        this.consumptionCalculatorMap = consumptionCalculatorMap;
    }

    @Override
    public List<Payment> createPaymentList(final Invoice invoice) throws UsageCalculationException, NegativeConsumptionValue {
        final List<Division> division = divisionService.createDivisionForResidence(invoice);
        final List<Apartment> apartments = extractApartmentsFromDivision(division);
        final IConsumptionCalculator calculator = createCalculator(invoice);
        final List<MediaUsage> usage = calculator.calculateConsumption(invoice, apartments);

        return generatePayments(invoice, division, usage);
    }

    private IConsumptionCalculator createCalculator(final Invoice invoice) {
        final Settings settings = settingsService.getSettings(invoice.getResidence());
        final String key = UsageCalculatorProvider.provideCalculator(settings.getWaterHeatingSystem(), invoice.getMedia());
        return consumptionCalculatorMap.get(key);
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

    private double sumUsage(List<MediaUsage> usage) {
        double sum = 0;
        for (MediaUsage i : usage) {
            sum += i.getUsage();
        }

        return sum;
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
