package kamienica.core.calculator;

import kamienica.core.exception.IncompatibleReadingType;
import kamienica.core.exception.NegativeConsumptionValue;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.reading.Reading;
import kamienica.feature.usagevalue.UsageValue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import javax.validation.constraints.NotNull;

public class StandardUsageCalculator implements ConsumptionCalculator {

    private LocalDate latestDate;
    private LocalDate previousDate;
    private Predicate<Reading> noNullApartment = s -> s.getMeter().getApartment() != null;
    private Predicate<Reading> maxDate = s -> s.getReadingDate().equals(latestDate);
    private Predicate<Reading> minDate = s -> s.getReadingDate().equals(previousDate);

    @Override
    public List<UsageValue> calculateConsumption(@NotNull final List<Apartment> apartment,
                                                 @NotNull final List<Reading> readings) throws NegativeConsumptionValue, IncompatibleReadingType {

        validateReadingType(readings);
        List<UsageValue> result = new ArrayList<>();
        latestDate = findNewestDate(readings);
        previousDate = findLatestDate(readings);

        for (Apartment ap : apartment) {
            double consumptionForElement = countUsage(ap, readings);
            UsageValue value = createDummyUsageValue(ap, consumptionForElement);
            result.add(value);
        }

        return result;
    }


    private UsageValue createDummyUsageValue(final Apartment ap, final double consumption) {
        final int daysBetween = Days.daysBetween(previousDate, latestDate).getDays();
        return new UsageValue("Użycie za " + ap.getDescription(), consumption, "unit", daysBetween, ap);
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

    private double sumUsageByDate(List<Reading> readings, Predicate<Reading> apartmentPredicate, Predicate<Reading> pred) {
        return readings.stream().filter(noNullApartment).filter(apartmentPredicate).filter(pred)
                .mapToDouble(o -> o.getValue()).sum();
    }

    private LocalDate findLatestDate(@NotNull List<Reading> readings) {
        return readings.stream().map(Reading::getReadingDate).min(LocalDate::compareTo).get();
    }

    private LocalDate findNewestDate(@NotNull List<Reading> readings) {
        return readings.stream().map(Reading::getReadingDate).max(LocalDate::compareTo).get();
    }

    private void validateReadingType(List<Reading> readings) throws IncompatibleReadingType {
        for (int i = 0; i < readings.size() - 1; i++) {
            Class clazz1 = readings.get(i).getClass();
            Class clazz2 = readings.get(i + 1).getClass();
            if (!clazz1.equals(clazz2)) {
                throw new IncompatibleReadingType("List contains readings of different type: " + clazz1.getSimpleName() + " vs. " + clazz2.getSimpleName());
            }
        }

    }
}
