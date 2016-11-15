package kamienica.core.util;

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

public class ConsumptionCalcImpl implements StreamConsumptionCalc {

    @Override
    public List<UsageValue> calculateConsumption(@NotNull List<Apartment> apartment, @NotNull List<Reading> readings) throws NegativeConsumptionValue {

        List<UsageValue> result = new ArrayList<>();

        LocalDate latestDate = readings.stream().map(u -> u.getReadingDate()).max(LocalDate::compareTo).get();
        LocalDate previousDate = readings.stream().map(u -> u.getReadingDate()).min(LocalDate::compareTo).get();

        for (Apartment ap : apartment) {
            double consumption = countUsage(ap, readings);
            UsageValue value = createDummyUsageValue(ap, latestDate, previousDate, consumption);
            result.add(value);
        }

        return result;
    }

    private UsageValue createDummyUsageValue(Apartment ap, LocalDate max, LocalDate min, double consumption) {
        int daysBetween = Days.daysBetween(max, min).getDays();
        return new UsageValue("Użycie za " + ap.getDescription(), consumption, "unit", daysBetween, ap);
    }

    private double countUsage(Apartment ap, List<Reading> readings) throws NegativeConsumptionValue {

        LocalDate latestDate = readings.stream().map(u -> u.getReadingDate()).max(LocalDate::compareTo).get();
        LocalDate previousDate = readings.stream().map(u -> u.getReadingDate()).min(LocalDate::compareTo).get();

        Predicate<Reading> apartmentPredicate = s -> s.getMeter().getApartment().getId().equals(ap.getId());
        Predicate<Reading> maxDate = s -> s.getReadingDate().equals(latestDate);
        Predicate<Reading> minDate = s -> s.getReadingDate().equals(previousDate);

        double consumption;
        consumption = -readings.stream().filter(apartmentPredicate).filter(minDate).mapToDouble(o -> o.getValue())
                .sum();
        consumption = +readings.stream().filter(apartmentPredicate).filter(maxDate).mapToDouble(o -> o.getValue())
                .sum();

        if (consumption < 0) {
            throw new NegativeConsumptionValue(consumption, ap);
        }
        return consumption;
    }

}
