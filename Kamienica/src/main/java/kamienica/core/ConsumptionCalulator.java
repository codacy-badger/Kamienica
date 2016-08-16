package kamienica.core;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Days;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.reading.ReadingAbstract;
import kamienica.feature.usagevalue.UsageValue;

public interface ConsumptionCalulator {

	public static List<UsageValue> calculateConsumption(List<Apartment> apartment,
			List<? extends ReadingAbstract> oldReadings, List<? extends ReadingAbstract> newReadings) {
		ArrayList<UsageValue> usageList = new ArrayList<UsageValue>();
		for (Apartment m : apartment) {

			UsageValue usageValue = new UsageValue();
			usageValue.setDescription("Zuzycie calkowite za: " + m.getDescription());
			usageValue.setApartment(m);
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
			usageList.add(usageValue);
		}

		return usageList;
	}

}
