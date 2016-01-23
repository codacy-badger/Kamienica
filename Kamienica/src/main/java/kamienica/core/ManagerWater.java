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

	// metoda stworzona gdy¿ nie istnieje fizyczny licznik wody dla czesci
	// administracyjnej a czeœæ wpólna jest wylczana jako ró¿nica miêdzy
	// licznikiem g³ownym a poszczególnymi licznikami
	public static ReadingWater generateUsageForAdministrativePart(List<ReadingWater> listaOdczytowWody,
			List<Apartment> apartments) {
		Apartment apartment = null;
		for (Apartment ap : apartments) {
			if (ap.getApartmentNumber() == 0) {
				apartment = ap;
				break;
			}
		}
		MeterWater temporaryWaterMeter = new MeterWater("Czêœæ Wspólna", "N/A", "m3", apartment, false);
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
		ReadingWater tmp = new ReadingWater(listaOdczytowWody.get(0).getReadingDate(), wartoscZuzyciaCzesciAdministracyjnej,
				temporaryWaterMeter);
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

	public static ArrayList<UsageValue> countWaterConsumption(
			List<Apartment> mieszkanie, List<ReadingWater> odczytWodaStare, List<ReadingWater> odczytWodaNowe) {

		ReadingWater czescWspolnaStare = ManagerWater.generateUsageForAdministrativePart(odczytWodaStare, mieszkanie);
		odczytWodaStare.add(czescWspolnaStare);
		ReadingWater czescWspolnaNowe = ManagerWater.generateUsageForAdministrativePart(odczytWodaNowe, mieszkanie);
		odczytWodaNowe.add(czescWspolnaNowe);


		ArrayList<UsageValue> generoWanaListaWartosciZuzycia = new ArrayList<UsageValue>();
		for (Apartment m : mieszkanie) {
			UsageValue tmp = new UsageValue();
			tmp.setDescription("Zuzycie calkowite za: " + m.getDescription());
			tmp.setApartment(m);
			double sumaPoprzednichOdczytowZaElement = 0;
			double sumaNowychOdczytowZaElement = 0;

			for (int indexOdczytow = 0; indexOdczytow < odczytWodaStare.size(); indexOdczytow++) {
				if (odczytWodaNowe.get(indexOdczytow).getMeter().getApartment() != null) {
					if (odczytWodaNowe.get(indexOdczytow).getMeter().getApartment().getApartmentNumber() == m
							.getApartmentNumber()) {
						sumaNowychOdczytowZaElement = sumaNowychOdczytowZaElement
								+ odczytWodaNowe.get(indexOdczytow).getValue();
					}
				}
				if (odczytWodaStare.get(indexOdczytow).getMeter().getApartment() != null) {
					if (odczytWodaStare.get(indexOdczytow).getMeter().getApartment().getApartmentNumber() == m
							.getApartmentNumber()) {
						sumaPoprzednichOdczytowZaElement = sumaPoprzednichOdczytowZaElement
								+ odczytWodaStare.get(indexOdczytow).getValue();
					}
				}
			}
			double zuzycie = sumaNowychOdczytowZaElement - sumaPoprzednichOdczytowZaElement;
			tmp.setUsage(zuzycie);
			tmp.setUnit(odczytWodaNowe.get(0).getUnit());
			tmp.setDaysBetweenReadings(Days.daysBetween(new DateTime(odczytWodaStare.get(0).getReadingDate()),
					new DateTime(odczytWodaNowe.get(0).getReadingDate())).getDays());
			generoWanaListaWartosciZuzycia.add(tmp);
		}

		return generoWanaListaWartosciZuzycia;

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
