package kamienica.core.calculator;

import kamienica.core.exception.NegativeConsumptionValue;
import kamienica.core.exception.UsageCalculationException;
import kamienica.core.util.CommonUtils;
import kamienica.model.Apartment;
import kamienica.model.MediaUsage;
import kamienica.model.Reading;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Standard calculation method
 */
@Component
public class StandardUsageCalculator implements ConsumptionCalculator {

    private LocalDate latestDate;
    private LocalDate previousDate;
    private Predicate<Reading> noNullApartment = s -> s.getMeter().getApartment() != null;
    private Predicate<Reading> maxDate = s -> s.getReadingDate().equals(latestDate);
    private Predicate<Reading> minDate = s -> s.getReadingDate().equals(previousDate);
    private double totalUsageCounted = 0;


    /**
     * Counts usage in most standard way.
     * The difference between main meter and the sum of submeters will be divided evenly among the tenants.
     *
     * @param apartment
     * @param readings
     * @return List<MediaUsage>
     * @throws NegativeConsumptionValue
     * @throws UsageCalculationException
     */
    @Override
    public List<MediaUsage> calculateConsumption(@NotNull final List<Apartment> apartment,
                                                 @NotNull final List<Reading> readings) throws NegativeConsumptionValue, UsageCalculationException {

        latestDate = findNewestDate(readings);
        previousDate = findLatestDate(readings);
        validateReadings(readings);
        List<MediaUsage> result = new ArrayList<>();


        for (Apartment ap : apartment) {
            MediaUsage value = countUsageForApartment(ap, readings);
            result.add(value);
        }
        checkCalculatedResult(result, readings);
        return result;
    }

    private void validateReadings(List<Reading> readings) throws UsageCalculationException {
        validateReadingType(readings);
        validateReadingDates(readings);
    }

    private void validateReadingDates(List<Reading> readings) throws UsageCalculationException {

        for (final Reading r : readings) {
            if(r.getReadingDate().isEqual(latestDate) ||  r.getReadingDate().isEqual(previousDate)) {
            } else  {
                throw new UsageCalculationException("There are more than two reading dates in the collection");
            }
        }

    }

    private void checkCalculatedResult(final List<MediaUsage> result, final List<Reading> readings) throws NegativeConsumptionValue {
        final double mainMeterUsage = countForMainMeter(readings);
        if (totalUsageCounted < mainMeterUsage) {
            final double difference = mainMeterUsage - totalUsageCounted;
            addDifferenceToSharedPart(result, difference);
        }
    }

    private void addDifferenceToSharedPart(List<MediaUsage> result, double difference) {

        for (MediaUsage aResult : result) {
            if (aResult.getApartment().getApartmentNumber() == 0) {
                double usageToChange = aResult.getUsage();
                usageToChange += difference;
                aResult.setUsage(usageToChange);
            }
        }
    }


    private MediaUsage countUsageForApartment(final Apartment ap, final List<Reading> readings) throws NegativeConsumptionValue {
        double consumption = countUsage(ap, readings);
        final int daysBetween = CommonUtils.countDaysBetween(previousDate, latestDate);
        totalUsageCounted += consumption;
        return new MediaUsage("Użycie za " + ap.getDescription(), consumption, "unit", daysBetween, ap);
    }


    private double countUsage(final Apartment ap, final List<Reading> readings) throws NegativeConsumptionValue {
        final Predicate<Reading> apartmentPredicate = s -> s.getMeter().getApartment().getId().equals(ap.getId());

        final double consumptionOld = sumUsageByDate(readings, apartmentPredicate, minDate);
        final double consumptionNew = sumUsageByDate(readings, apartmentPredicate, maxDate);
        final double consumption = consumptionNew - consumptionOld;

        if (consumption < 0) {
            throw new NegativeConsumptionValue(consumption, ap);
        }
        return consumption;
    }

    private double countForMainMeter(final List<Reading> readings) throws NegativeConsumptionValue {
        final Predicate<Reading> apartmentPredicate = s -> s.getMeter().getApartment() == null;

        final double consumptionOld = sumUsageForMain(readings, apartmentPredicate, minDate);
        final double consumptionNew = sumUsageForMain(readings, apartmentPredicate, maxDate);
        final double consumption = consumptionNew - consumptionOld;

        if (consumption < 0) {
            throw new NegativeConsumptionValue(consumption, null);
        }
        return consumption;
    }

    private double sumUsageByDate(List<Reading> readings, Predicate<Reading> apartmentPredicate, Predicate<Reading> pred) {
        return readings.stream().filter(noNullApartment).filter(apartmentPredicate).filter(pred)
                .mapToDouble(o -> o.getValue()).sum();
    }

    private double sumUsageForMain(List<Reading> readings, Predicate<Reading> apartmentPredicate, Predicate<Reading> pred) {
        return readings.stream().filter(apartmentPredicate).filter(pred)
                .mapToDouble(o -> o.getValue()).sum();
    }

    private LocalDate findLatestDate(@NotNull List<Reading> readings) {
        return readings.stream().map(Reading::getReadingDate).min(LocalDate::compareTo).get();
    }

    private LocalDate findNewestDate(@NotNull List<Reading> readings) {
        return readings.stream().map(Reading::getReadingDate).max(LocalDate::compareTo).get();
    }

    private void validateReadingType(List<Reading> readings) throws UsageCalculationException {
        for (int i = 0; i < readings.size() - 1; i++) {
            Class clazz1 = readings.get(i).getClass();
            Class clazz2 = readings.get(i + 1).getClass();
            if (!clazz1.equals(clazz2)) {
                throw new UsageCalculationException("List contains readings of different type: " + clazz1.getSimpleName() + " vs. " + clazz2.getSimpleName());
            }
        }

    }
}
