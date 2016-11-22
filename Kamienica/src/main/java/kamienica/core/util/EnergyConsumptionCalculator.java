package kamienica.core.util;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Days;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.usagevalue.UsageValue;

public class EnergyConsumptionCalculator {

	public static List<UsageValue> countConsumption(List<Apartment> apartment, List<ReadingEnergy> oldReadings,
													List<ReadingEnergy> newReadings) {
		ArrayList<UsageValue> out = new ArrayList<>();
		for (Apartment m : apartment) {

			UsageValue usageValue = createNewUsageValue(m);
			double sumPrevious = 0;
			double sumCurrent = 0;

			for (int i = 0; i < newReadings.size(); i++) {
				if (newReadings.get(i).getMeter().getApartment() != null) {
					if (newReadings.get(i).getMeter().getApartment().getApartmentNumber() == m.getApartmentNumber()) {
						sumCurrent = sumCurrent + newReadings.get(i).getValue();
					}
				}
				if (!oldReadings.isEmpty()) {
					if (oldReadings.get(i).getMeter().getApartment() != null) {
						if (oldReadings.get(i).getMeter().getApartment().getApartmentNumber() == m
								.getApartmentNumber()) {
							sumPrevious = sumPrevious + oldReadings.get(i).getValue();
						}
					}
				}
			}

			double usage = sumCurrent - sumPrevious;

			usageValue.setUsage(usage);
			usageValue.setUnit(newReadings.get(0).getUnit());
			if (oldReadings.isEmpty()) {
				usageValue.setDaysBetweenReadings(0);
			} else {
				usageValue.setDaysBetweenReadings(
						Days.daysBetween(oldReadings.get(0).getReadingDate(), newReadings.get(0).getReadingDate())
								.getDays());
			}
			out.add(usageValue);
		}

		return out;

	}

	private static UsageValue createNewUsageValue(Apartment m) {
		UsageValue usageValue = new UsageValue();
		usageValue.setDescription("Zuzycie calkowite za: " + m.getDescription());
		usageValue.setApartment(m);
		return usageValue;
	}
}
