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

	private LocalDate latestDate;
	private LocalDate previousDate;
	private Predicate<Reading> noNullApartment = s -> s.getMeter().getApartment() != null;
	private Predicate<Reading> maxDate = s -> s.getReadingDate().equals(latestDate);
	private Predicate<Reading> minDate = s -> s.getReadingDate().equals(previousDate);

	@Override
	public List<UsageValue> calculateConsumption(@NotNull final List<Apartment> apartment,
			@NotNull final List<Reading> readings) throws NegativeConsumptionValue {

		List<UsageValue> result = new ArrayList<>();

		latestDate = readings.stream().map(u -> u.getReadingDate()).max(LocalDate::compareTo).get();
		previousDate = readings.stream().map(u -> u.getReadingDate()).min(LocalDate::compareTo).get();

		for (Apartment ap : apartment) {
			double consumption = countUsage(ap, readings);
			UsageValue value = createDummyUsageValue(ap, consumption);
			result.add(value);
		}

		return result;
	}

	private UsageValue createDummyUsageValue(final Apartment ap, final double consumption) {
		int daysBetween = Days.daysBetween(previousDate, latestDate).getDays();
		return new UsageValue("Użycie za " + ap.getDescription(), consumption, "unit", daysBetween, ap);
	}

	private double countUsage(final Apartment ap, final List<Reading> readings) throws NegativeConsumptionValue {

		final Predicate<Reading> apartmentPredicate = s -> s.getMeter().getApartment().getId().equals(ap.getId());

		double consumptionOld = readings.stream().filter(noNullApartment).filter(apartmentPredicate).filter(minDate)
				.mapToDouble(o -> o.getValue()).sum();
		double consumptionNew = readings.stream().filter(noNullApartment).filter(apartmentPredicate).filter(maxDate)
				.mapToDouble(o -> o.getValue()).sum();
		final double consumption = consumptionNew - consumptionOld;

		if (consumption < 0) {
			throw new NegativeConsumptionValue(consumption, ap);
		}
		return consumption;
	}

}
