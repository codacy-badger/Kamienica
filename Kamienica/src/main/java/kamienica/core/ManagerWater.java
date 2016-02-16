package kamienica.core;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;

import kamienica.model.Apartment;
import kamienica.model.MeterWater;
import kamienica.model.ReadingWater;
import kamienica.model.UsageValue;

public class ManagerWater {

	// metoda stworzona gdy� nie istnieje fizyczny licznik wody dla czesci
	// administracyjnej a cześć wpólna jest wylczana jako rónica między
	// licznikiemgłównym a poszczególnymi licznikami
	public static ReadingWater generateUsageForAdministrativePart(List<ReadingWater> listaOdczytowWody,
			List<Apartment> apartments) {
		Apartment apartment = null;
		for (Apartment ap : apartments) {
			if (ap.getApartmentNumber() == 0) {
				apartment = ap;
				break;
			}
		}
		MeterWater temporaryWaterMeter = new MeterWater("Część Wspólna", "N/A", "m3", apartment, false);
		double sumaZuzyciaPozostalychLicznikow = 0;
		double zuzycieLicznikaGlownego = 0;

		for (ReadingWater odczyt : listaOdczytowWody) {
			if (odczyt.getMeter().getApartment() != null) {
				sumaZuzyciaPozostalychLicznikow += odczyt.getValue();
			} else {
				zuzycieLicznikaGlownego = odczyt.getValue();
			}
		}
		double wartoscZuzyciaCzesciAdministracyjnej = zuzycieLicznikaGlownego - sumaZuzyciaPozostalychLicznikow;
		ReadingWater tmp = new ReadingWater(listaOdczytowWody.get(0).getReadingDate(),
				wartoscZuzyciaCzesciAdministracyjnej, temporaryWaterMeter);
		return tmp;
	}

	// metoda dla managera gazu
	public static double countWarmWaterUsage(List<ReadingWater> staryOdczytWody, List<ReadingWater> nowyOdczytWody) {
		double output = 0;

		for (ReadingWater o : nowyOdczytWody) {
			if (o.getMeter().getIsWarmWater() == true)
				output += o.getValue();
		}

		for (ReadingWater o : staryOdczytWody) {
			if (o.getMeter().getIsWarmWater() == true)
				output = output - o.getValue();
		}

		return output;
	}

	public static ArrayList<UsageValue> countWaterConsumption(List<Apartment> apartment, List<ReadingWater> oldReading,
			List<ReadingWater> newRading) {

		ReadingWater sharedReadingOld;
		if (!oldReading.isEmpty()) {
			sharedReadingOld = ManagerWater.generateUsageForAdministrativePart(oldReading, apartment);
			oldReading.add(sharedReadingOld);
		}
		ReadingWater sharedReadingNew = ManagerWater.generateUsageForAdministrativePart(newRading, apartment);
		newRading.add(sharedReadingNew);

		ArrayList<UsageValue> usage = new ArrayList<UsageValue>();
		for (Apartment m : apartment) {
			UsageValue tmpUsage = new UsageValue();
			tmpUsage.setDescription("Zuzycie calkowite za: " + m.getDescription());
			tmpUsage.setApartment(m);
			double sumPrevious = 0;
			double sumNew = 0;

			for (int idx = 0; idx < newRading.size(); idx++) {
				if (newRading.get(idx).getMeter().getApartment() != null) {
					if (newRading.get(idx).getMeter().getApartment().getApartmentNumber() == m.getApartmentNumber()) {
						sumNew = sumNew + newRading.get(idx).getValue();
					}
				}
				if (!oldReading.isEmpty()) {
					if (oldReading.get(idx).getMeter().getApartment() != null) {
						if (oldReading.get(idx).getMeter().getApartment().getApartmentNumber() == m
								.getApartmentNumber()) {
							sumPrevious = sumPrevious + oldReading.get(idx).getValue();
						}
					}
				}
			}
			double zuzycie = sumNew - sumPrevious;
			tmpUsage.setUsage(zuzycie);
			tmpUsage.setUnit(newRading.get(0).getUnit());
			if (!oldReading.isEmpty()) {
				tmpUsage.setDaysBetweenReadings(Days.daysBetween(new DateTime(oldReading.get(0).getReadingDate()),
						new DateTime(newRading.get(0).getReadingDate())).getDays());
			} else {
				tmpUsage.setDaysBetweenReadings(0);
			}
			usage.add(tmpUsage);
		}

		return usage;

	}

	public static Apartment getSharedApartment(List<Apartment> apartments) {
		Apartment ap = null;
		for (Apartment a : apartments) {
			if (a.getApartmentNumber() == 0) {
				ap = a;
				break;
			}
		}

		return ap;
	}
}
