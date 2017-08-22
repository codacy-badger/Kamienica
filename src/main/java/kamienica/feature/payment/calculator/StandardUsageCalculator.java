package kamienica.feature.payment.calculator;

import kamienica.core.util.CommonUtils;
import kamienica.feature.reading.IReadingService;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import kamienica.model.exception.NegativeConsumptionValue;
import kamienica.model.exception.UsageCalculationException;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Standard calculation method
 */
@Service(value = StandardUsageCalculator.TYPE)
@Transactional
public class StandardUsageCalculator implements IConsumptionCalculator {

    static final String TYPE= "STANDARD";
    private final IReadingService readingService;

    @Autowired
    public StandardUsageCalculator(IReadingService readingService) {
        this.readingService = readingService;
    }

    /**
     * Counts usage in most standard way.
     * The difference between main meter and the sum of submeters will be divided evenly among the tenants.
     *
     * @param invoice
     * @param apartments
     * @return List<MediaUsage>
     */
    @Override
    public List<MediaUsage> calculateConsumption(final Invoice invoice, final List<Apartment> apartments) {
        final List<Reading> newReadings = readingService.getForInvoice(invoice);
        final List<Reading> oldReadins = readingService.getPreviousReading(invoice);
        final List<MediaUsage> result = new ArrayList<>();


        for (final Apartment ap : apartments) {
            final MediaUsage value = countUsageForApartment(ap, oldReadins, newReadings);
            result.add(value);
        }
        recalculateSharedPartConsuption(oldReadins, newReadings, result);
        return result;
    }

    private void recalculateSharedPartConsuption(final List<Reading> oldReadins, final List<Reading> newReadings, final List<MediaUsage> result) {
        final double oldMainMeterUsage = oldReadins.stream().filter(x ->x.getMeter().getApartment() == null).mapToDouble(x->x.getValue()).sum();
        final double newMainMeterUsage = newReadings.stream().filter(x ->x.getMeter().getApartment() == null).mapToDouble(x->x.getValue()).sum();
        final double mainMeterUsage = newMainMeterUsage - oldMainMeterUsage;
        final double calculatedResult = result.stream().mapToDouble(x -> x.getUsage()).sum();
        if(mainMeterUsage > calculatedResult) {
            final MediaUsage sharedPart = result.stream().filter(x -> x.getApartment().getApartmentNumber()==0).findFirst().get();
            final double currentUsage = sharedPart.getUsage();
            sharedPart.setUsage(currentUsage + (mainMeterUsage - calculatedResult));
        }
    }

    private MediaUsage countUsageForApartment(final Apartment ap, final List<Reading> oldReadins, final List<Reading> newReadings) {
        final double totalUsageForNewReadings = getSum(newReadings, ap);
        final double totalUsageForOldReadings = getSum(oldReadins, ap);
        final double consumption = totalUsageForNewReadings - totalUsageForOldReadings;
        return new MediaUsage(consumption,  ap);
    }

    private double getSum(List<Reading> newReadings, final Apartment ap) {
        final Predicate<Reading> notNull = x -> x.getMeter().getApartment() != null;
        final Predicate<Reading> thisApartment = x -> x.getMeter().getApartment().equals(ap);
        return newReadings.stream()
                .filter(notNull)
                .filter(thisApartment)
                .mapToDouble(x -> x.getValue())
                .sum();
    }
}
