package kamienica.core;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Days;

import kamienica.model.Apartment;
import kamienica.model.ReadingEnergy;
import kamienica.model.UsageValue;

public class ManagerEnergy {

	public static ArrayList<UsageValue> countEnergyConsupmtion(ArrayList<Apartment> apartment,
			ArrayList<ReadingEnergy> oldReadings, ArrayList<ReadingEnergy> newReadings) {
		ArrayList<UsageValue> out = new ArrayList<UsageValue>();
		for (Apartment m : apartment) {
			UsageValue tmp = new UsageValue();
			tmp.setDescription("Zuzycie calkowite za: " + m.getDescription());
			tmp.setApartment(m);
			double sumPrevious = 0;
			double sumCurrent = 0;

			for (int i = 0; i < oldReadings.size(); i++) {
				if (newReadings.get(i).getMeter().getApartment() != null) {
					if (newReadings.get(i).getMeter().getApartment().getApartmentNumber() == m.getApartmentNumber()) {
						sumCurrent = sumCurrent + newReadings.get(i).getValue();
					}
				}

				if (oldReadings.get(i).getMeter().getApartment() != null) {
					if (oldReadings.get(i).getMeter().getApartment().getApartmentNumber() == m.getApartmentNumber()) {
						sumPrevious = sumPrevious + oldReadings.get(i).getValue();
					}
				}
			}
			double usage = sumCurrent - sumPrevious;
			tmp.setUsage(usage);
			tmp.setUnit(newReadings.get(0).getUnit());
			tmp.setDaysBetweenReadings(Days.daysBetween(new DateTime(oldReadings.get(0).getReadingDate()),
					new DateTime(newReadings.get(0).getReadingDate())).getDays());
			out.add(tmp);
		}

		return out;

	}
}
