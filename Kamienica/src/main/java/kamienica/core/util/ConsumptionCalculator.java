package kamienica.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.joda.time.Days;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.reading.ReadingAbstract;
import kamienica.feature.usagevalue.UsageValue;

public class ConsumptionCalculator {

	Predicate<Apartment> notNull = p -> p != null;

	public List<UsageValue> countConsupmtion(List<Apartment> apartment, List<ReadingAbstract> oldReadings,
			List<ReadingAbstract> newReadings) {
		List<UsageValue> output = new ArrayList<UsageValue>();
		for (Apartment m : apartment) {
//scasd
			UsageValue usageValue = new UsageValue();
			usageValue.setDescription("Zuzycie calkowite za: " + m.getDescription());
			usageValue.setApartment(m);
			double sumPrevious = 0;
			double sumCurrent = 0;

			for (int i = 0; i < newReadings.size(); i++) {
				sumCurrent += extractReadingValue(m, newReadings.get(i));
			}

			for (int i = 0; i < oldReadings.size(); i++) {
				sumPrevious += extractReadingValue(m, oldReadings.get(i));
			}

			usageValue.setUsage((sumCurrent - sumPrevious));
			usageValue.setUnit(newReadings.get(0).getUnit());
			if (oldReadings.isEmpty()) {
				usageValue.setDaysBetweenReadings(0);
			} else {
				usageValue.setDaysBetweenReadings(
						Days.daysBetween(oldReadings.get(0).getReadingDate(), newReadings.get(0).getReadingDate())
								.getDays());
				// tmp.setDaysBetweenReadings(Days.daysBetween(new
				// DateTime(oldReadings.get(0).getReadingDate()),
				// new
				// DateTime(newReadings.get(0).getReadingDate())).getDays());
			}
			output.add(usageValue);
		}

		return output;

	}

//	private UsageValue createUsageValueForApartment(Apartment m, List<ReadingAbstract> oldReadings,
//			List<ReadingAbstract> newReadings) {
//		UsageValue usageValue = new UsageValue();
//		usageValue.setDescription("Zuzycie calkowite za: " + m.getDescription());
//		usageValue.setApartment(m);
//		usageValue.setUsage(calulateUsage());
//		return usageValue;
//	}
//
//	private double calulateUsage(Apartment m, List<ReadingAbstract> oldReadings) {
//		double sumPrevious = getReadingValuesForApartment;
//		double sumCurrent = 0;
//		return 0;
//	}

	private double getReadingValuesForApartment(Apartment apartment, List<ReadingAbstract> list) {
		double output = 0;

		for (int i = 0; i < list.size(); i++) {
			output += extractReadingValue(apartment, list.get(i));
		}
		return output;
	}

	private double extractReadingValue(Apartment apartment, ReadingAbstract reading) {
		if (reading.getMeter().getApartment() != null) {
			if (reading.getMeter().getApartment().getApartmentNumber() == apartment.getApartmentNumber()) {
				return reading.getValue();
			}
		}

		return 0;
	}
}
