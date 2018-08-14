package kamienica.feature.payment.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import kamienica.feature.division.IDivisionService;
import kamienica.feature.settings.ISettingsService;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Division;
import kamienica.model.entity.Invoice;
import kamienica.model.entity.MediaUsage;
import kamienica.model.entity.Payment;
import kamienica.model.entity.Settings;
import kamienica.model.entity.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentCalculator implements IPaymentCalculator{

    private final ISettingsService settingsService;
    private final IDivisionService divisionService;
    private final Map<String, IConsumptionCalculator> consumptionCalculatorMap;

    @Autowired
    public PaymentCalculator(final ISettingsService settingsService, final IDivisionService divisionService,
                             final Map<String, IConsumptionCalculator> consumptionCalculatorMap) {
        this.settingsService = settingsService;
        this.divisionService = divisionService;
        this.consumptionCalculatorMap = consumptionCalculatorMap;
    }

    @Override
    public List<Payment> createPaymentList(final Invoice invoice) {
        final List<Division> division = divisionService.createDivisionForResidence(invoice);
        final List<Apartment> apartments = extractApartmentsFromDivision(division);
        final IConsumptionCalculator calculator = createCalculator(invoice);
        final List<MediaUsage> usage = calculator.calculateConsumption(invoice, apartments);

        return generatePayments(invoice, division, usage);
    }

    private IConsumptionCalculator createCalculator(final Invoice invoice) {
        final Settings settings = settingsService.getSettings(invoice.getResidence());
        if(settings == null || settings.getWaterHeatingSystem() == null) {
            throw new NoSettingsException("Setting must be set first");
        }
        final String key = UsageCalculatorProvider.provideCalculator(settings.getWaterHeatingSystem(), invoice.getMedia());
        return consumptionCalculatorMap.get(key);
    }

    private List<Payment> generatePayments(final Invoice invoice,
                                           final List<Division> division, final List<MediaUsage> usage) {
        final double sumOfExpenses = invoice.getTotalAmount();
        final List<Payment> listToReturn = new ArrayList<>();

        double usageSum = sumUsage(usage);

        final List<Tenant> tenants = extractTenantsFromDivision(division);
        for (final Tenant tenant : tenants) {
            final HashMap<Integer, Double> divisionForTenant = setTenantDivision(division, tenant);
            final double amount = usage.stream()
                .mapToDouble(x -> {
                    final double factor = x.getUsage() / usageSum;
                    return sumOfExpenses * factor * divisionForTenant
                        .get(x.getApartment().getApartmentNumber());
                })
                .sum();

            final Payment forList = new Payment(amount, tenant, invoice);
            listToReturn.add(forList);
        }
        return listToReturn;
    }

    private List<Apartment> extractApartmentsFromDivision(final List<Division> division) {
        return division.stream().map(Division::getApartment).distinct().collect(Collectors.toList());
    }

    private double sumUsage(final List<MediaUsage> usage) {
        return usage.stream().mapToDouble(MediaUsage::getUsage).sum();
    }

    private HashMap<Integer, Double> setTenantDivision(final List<Division> division, final Tenant tenant) {
        final HashMap<Integer, Double> output = new HashMap<>();
        division.stream().filter(p -> tenant.getId().equals(p.getTenant().getId())).forEachOrdered(p -> {
            output.put(p.getApartment().getApartmentNumber(), p.getDivisionValue());
        });
        return output;
    }

    private List<Tenant> extractTenantsFromDivision(final List<Division> division) {
        return division.stream().map(Division::getTenant).distinct().collect(Collectors.toList());
    }
}
