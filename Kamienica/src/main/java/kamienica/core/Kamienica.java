package kamienica.core;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedList;

public class Kamienica {

	public static void main(String[] args) throws IOException, ParseException {

		LinkedList<Integer> test = new LinkedList<>();
		test.addAll(Arrays.asList(1,1,1,1,1,1));
		System.out.println(test.toString());
		for (Integer i :test) {
			i += 5;
			System.out.println("i po zmianach " + i);
		}
		System.out.println(test.toString());
		
//		Invoice testr = new InvoiceEnergy();
//		testr.setBaseReading();
		
//		List<Test> testList = new ArrayList<>();
//		testList = testList.stream().filter(t -> t=1).collect(Collectors.toList());
//		
//		test = test.strea
		// -------------------mieszkania_najemcy_algorytm_podzialu--------------------------------------------------------
		
		
		
		
//		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//		Apartment mieszkanie0 = new Apartment(1, 0, "0000", "Czesc Wspolna");
//		Apartment mieszkanie1 = new Apartment(2, 1, "1111", "Piwnica");
//		Apartment mieszkanie2 = new Apartment(3, 2, "2222", "Parter");
//		Apartment mieszkanie3 = new Apartment(4, 3, "3333", "1 pietro");
//		Apartment mieszkanie4 = new Apartment(5, 4, "4444", "2 pietro");
//
//		ArrayList<Apartment> listaMieszkan = new ArrayList<Apartment>();
//		listaMieszkan.add(mieszkanie0);
//		listaMieszkan.add(mieszkanie1);
//		listaMieszkan.add(mieszkanie2);
//		listaMieszkan.add(mieszkanie3);
//		listaMieszkan.add(mieszkanie4);
//	
//		
//
//		Tenant najemca1 = new Tenant("brak(1)", "brak", "brak", "0", mieszkanie1);
//		Tenant najemca2 = new Tenant("Jan(2)", "Kowalski", "kowalski@wp.pl", "222222", mieszkanie2);
//		Tenant najemca3 = new Tenant("Adam(3)", "Nowak", "nowak@wp.pl", "111111", mieszkanie3);
//		Tenant najemca4 = new Tenant("Andrzej(4)", "Baranowski", "baranowski@wp.pl", "444444", mieszkanie4);
//
//		ArrayList<Tenant> listaNajemcow = new ArrayList<Tenant>();
//		listaNajemcow.add(najemca1);
//		listaNajemcow.add(najemca2);
//		listaNajemcow.add(najemca3);
//		listaNajemcow.add(najemca4);
//
//		ArrayList<Division> algorytmPodzialuTmp = new ArrayList<Division>();
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca1, mieszkanie0, 0.25));
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca1, mieszkanie1, 1));
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca1, mieszkanie2, 0));
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca1, mieszkanie3, 0));
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca1, mieszkanie4, 0));
//
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca2, mieszkanie0, 0.25));
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca2, mieszkanie1, 0));
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca2, mieszkanie2, 1));
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca2, mieszkanie3, 0));
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca2, mieszkanie4, 0));
//
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca3, mieszkanie0, 0.25));
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca3, mieszkanie1, 0));
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca3, mieszkanie2, 0));
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca3, mieszkanie3, 1));
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca3, mieszkanie4, 0));
//
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca4, mieszkanie0, 0.25));
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca4, mieszkanie1, 0));
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca4, mieszkanie2, 0));
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca4, mieszkanie3, 0));
//		algorytmPodzialuTmp.add(new Division(0, format.parse("20-07-2015"), najemca4, mieszkanie4, 1));
//
//		// ------------------------------LICZNIKI--------------------------------------------------------------------
//
//		// --------------------------ENERGIA-------------------------------------------------------------
//
//		MeterEnergy licznikGlownyEnergii = new MeterEnergy("Glowny Licznik energii", "354", "kWh", null);
//		MeterEnergy licznikEnergiiM1 = new MeterEnergy("Licznik Energii M1", "89/0", "kWh", listaMieszkan.get(1));
//		MeterEnergy licznikEnergiiM2 = new MeterEnergy("Licznik Energii M2", "89/0", "kWh", listaMieszkan.get(2));
//		MeterEnergy licznikEnergiiM3 = new MeterEnergy("Licznik Energii M3", "89/0", "kWh", listaMieszkan.get(3));
//		MeterEnergy licznikEnergiiM4 = new MeterEnergy("Licznik Energii M4", "89/0", "kWh", listaMieszkan.get(4));
//		MeterEnergy licznikEnergiiCzescWspolna = new MeterEnergy("Licznik czesci administracyjnej", "89/0", "kWh",
//				listaMieszkan.get(0));
//
//		ArrayList<MeterEnergy> listaLicznikowEnergii = new ArrayList<MeterEnergy>();
//		listaLicznikowEnergii.add(licznikGlownyEnergii);
//		listaLicznikowEnergii.add(licznikEnergiiM1);
//		listaLicznikowEnergii.add(licznikEnergiiM2);
//		listaLicznikowEnergii.add(licznikEnergiiM3);
//		listaLicznikowEnergii.add(licznikEnergiiM4);
//		listaLicznikowEnergii.add(licznikEnergiiCzescWspolna);
//
//		ReadingEnergy poprzedniOdczytLicznikaGlownegoEnergii = new ReadingEnergy(format.parse("2014-12-12"), 0,
//				licznikGlownyEnergii);
//		ReadingEnergy poprzedniOdczytlicznikEnergiiM1 = new ReadingEnergy(format.parse("2014-12-12"), 0,
//				licznikEnergiiM1);
//		ReadingEnergy poprzedniOdczytlicznikEnergiiM2 = new ReadingEnergy(format.parse("2014-12-12"), 0,
//				licznikEnergiiM2);
//		ReadingEnergy poprzedniOdczytlicznikEnergiiM3 = new ReadingEnergy(format.parse("2014-12-12"), 0,
//				licznikEnergiiM3);
//		ReadingEnergy poprzedniOdczytlicznikEnergiiM4 = new ReadingEnergy(format.parse("2014-12-12"), 0,
//				licznikEnergiiM4);
//		ReadingEnergy poprzedniOdczytlicznikEnergiiCzescWspolna = new ReadingEnergy(format.parse("2014-12-12"), 0,
//				licznikEnergiiCzescWspolna);
//
//		ArrayList<ReadingEnergy> poprzednieOdczytyEnergii = new ArrayList<ReadingEnergy>();
//		poprzednieOdczytyEnergii.add(poprzedniOdczytLicznikaGlownegoEnergii);
//		poprzednieOdczytyEnergii.add(poprzedniOdczytlicznikEnergiiM1);
//		poprzednieOdczytyEnergii.add(poprzedniOdczytlicznikEnergiiM2);
//		poprzednieOdczytyEnergii.add(poprzedniOdczytlicznikEnergiiM3);
//		poprzednieOdczytyEnergii.add(poprzedniOdczytlicznikEnergiiM4);
//		poprzednieOdczytyEnergii.add(poprzedniOdczytlicznikEnergiiCzescWspolna);
//
//		ReadingEnergy odczytLicznikGlownYEnergii = new ReadingEnergy(new Date(), 498, licznikGlownyEnergii);
//		ReadingEnergy odczytLicznikEnergiiM1 = new ReadingEnergy(new Date(), 2, licznikEnergiiM1);
//		ReadingEnergy odczytLicznikEnergiiM2 = new ReadingEnergy(new Date(), 30, licznikEnergiiM2);
//		ReadingEnergy odczytLicznikEnergiiM3 = new ReadingEnergy(new Date(), 65, licznikEnergiiM3);
//		ReadingEnergy odczytLicznikEnergiiM4 = new ReadingEnergy(new Date(), 46, licznikEnergiiM4);
//		ReadingEnergy odczytLicznikEnergiiCzescWspolna = new ReadingEnergy(new Date(), 40, licznikEnergiiCzescWspolna);
//
//		ArrayList<ReadingEnergy> noweOdczytyEnergii = new ArrayList<ReadingEnergy>();
//		noweOdczytyEnergii.add(odczytLicznikGlownYEnergii);
//		noweOdczytyEnergii.add(odczytLicznikEnergiiM1);
//		noweOdczytyEnergii.add(odczytLicznikEnergiiM2);
//		noweOdczytyEnergii.add(odczytLicznikEnergiiM3);
//		noweOdczytyEnergii.add(odczytLicznikEnergiiM4);
//		noweOdczytyEnergii.add(odczytLicznikEnergiiCzescWspolna);
//
//		System.out.println("odczyty stare");
//		System.out.println(poprzednieOdczytyEnergii.toString());
//		System.out.println("odczyty nowe");
//		System.out.println(noweOdczytyEnergii.toString());
//
//		// ------------------------Woda-----------------------------------------------------------------------
//
//		MeterWater licznikGlownyWody = new MeterWater("Licznik woda glowny", "55", "m3", null, false);
//		MeterWater licznikWodaZimnaM1 = new MeterWater("woda zimna m1", "434", "metry3", listaMieszkan.get(1), false);
//		MeterWater licznikWodaCieplaM1 = new MeterWater("woda ciepla m1", "3455", "metry3", listaMieszkan.get(1),
//				true);
//		MeterWater licznikWodaZimnaM2 = new MeterWater("woda zimna m2", "434", "metry3", listaMieszkan.get(2), false);
//		MeterWater licznikWodaCieplaM2 = new MeterWater("woda ciepla m2", "3455", "metry3", listaMieszkan.get(2),
//				true);
//		MeterWater licznikWodaZimnaM3 = new MeterWater("woda zimna m3", "434", "metry3", listaMieszkan.get(3), false);
//		MeterWater licznikWodaCieplaM3 = new MeterWater("woda ciepla m3", "3455", "metry3", listaMieszkan.get(3),
//				true);
//		MeterWater licznikWodaZimnaM4 = new MeterWater("woda zimna m4", "434", "metry3", listaMieszkan.get(4), false);
//		MeterWater licznikWodaCieplaM4 = new MeterWater("woda ciepla m4", "3455", "metry3", listaMieszkan.get(4),
//				true);
////		MeterWater licznikWodaCzescWspolna = new MeterWater("woda czesc wspolna", "345", "metry3",
////				listaMieszkan.get(0), false);
//
//		ArrayList<MeterWater> listaLicznikowWody = new ArrayList<MeterWater>();
//		listaLicznikowWody.add(licznikGlownyWody);
//		listaLicznikowWody.add(licznikWodaZimnaM1);
//		listaLicznikowWody.add(licznikWodaCieplaM1);
//		listaLicznikowWody.add(licznikWodaZimnaM2);
//		listaLicznikowWody.add(licznikWodaCieplaM2);
//		listaLicznikowWody.add(licznikWodaZimnaM3);
//		listaLicznikowWody.add(licznikWodaCieplaM3);
//		listaLicznikowWody.add(licznikWodaZimnaM4);
//		listaLicznikowWody.add(licznikWodaCieplaM4);
//
//		ReadingWater poprzedniOdczytGlownegoLicznikaWody = new ReadingWater(format.parse("2014-12-12"), 0,
//				licznikGlownyWody);
//		ReadingWater poprzedniOdczytWodyZimnejM1 = new ReadingWater(format.parse("2014-12-12"), 0, licznikWodaZimnaM1);
//		ReadingWater poprzedniOdczytWodyCieplejM1 = new ReadingWater(format.parse("2014-12-12"), 0, licznikWodaCieplaM1);
//		ReadingWater poprzedniOdczytWodyZimnejM2 = new ReadingWater(format.parse("2014-12-12"), 0, licznikWodaZimnaM2);
//		ReadingWater poprzedniOdczytWodyCieplejM2 = new ReadingWater(format.parse("2014-12-12"), 0, licznikWodaCieplaM2);
//		ReadingWater poprzedniOdczytWodyZimnejM3 = new ReadingWater(format.parse("2014-12-12"), 0, licznikWodaZimnaM3);
//		ReadingWater poprzedniOdczytWodyCieplejM3 = new ReadingWater(format.parse("2014-12-12"), 0, licznikWodaCieplaM3);
//		ReadingWater poprzedniOdczytWodyZimnejM4 = new ReadingWater(format.parse("2014-12-12"), 0, licznikWodaZimnaM4);
//		ReadingWater poprzedniOdczytWodyCieplejM4 = new ReadingWater(format.parse("2014-12-12"), 0, licznikWodaCieplaM4);
//
//		ArrayList<ReadingWater> listaPoprzednichOdczytowWody = new ArrayList<ReadingWater>();
//		listaPoprzednichOdczytowWody.add(poprzedniOdczytGlownegoLicznikaWody);
//		listaPoprzednichOdczytowWody.add(poprzedniOdczytWodyZimnejM1);
//		listaPoprzednichOdczytowWody.add(poprzedniOdczytWodyCieplejM1);
//		listaPoprzednichOdczytowWody.add(poprzedniOdczytWodyZimnejM2);
//		listaPoprzednichOdczytowWody.add(poprzedniOdczytWodyCieplejM2);
//		listaPoprzednichOdczytowWody.add(poprzedniOdczytWodyZimnejM3);
//		listaPoprzednichOdczytowWody.add(poprzedniOdczytWodyCieplejM3);
//		listaPoprzednichOdczytowWody.add(poprzedniOdczytWodyZimnejM4);
//		listaPoprzednichOdczytowWody.add(poprzedniOdczytWodyCieplejM4);
//
//		ReadingWater nowyOdczytGlownegoLicznikaWody = new ReadingWater(new Date(), 950, licznikGlownyWody);
//		ReadingWater nowyOdczytWodyZimnejM1 = new ReadingWater(new Date(), 10, licznikWodaZimnaM1);
//		ReadingWater nowyOdczytWodyCieplejM1 = new ReadingWater(new Date(), 40, licznikWodaCieplaM1);
//		ReadingWater nowyOdczytWodyZimnejM2 = new ReadingWater(new Date(), 100, licznikWodaZimnaM2);
//		ReadingWater nowyOdczytWodyCieplejM2 = new ReadingWater(new Date(), 100, licznikWodaCieplaM2);
//		ReadingWater nowyOdczytWodyZimnejM3 = new ReadingWater(new Date(), 100, licznikWodaZimnaM3);
//		ReadingWater nowyOdczytWodyCieplejM3 = new ReadingWater(new Date(), 150, licznikWodaCieplaM3);
//		ReadingWater nowyOdczytWodyZimnejM4 = new ReadingWater(new Date(), 100, licznikWodaZimnaM4);
//		ReadingWater nowyOdczytWodyCieplejM4 = new ReadingWater(new Date(), 300, licznikWodaCieplaM4);
//
//		ArrayList<ReadingWater> listaNowychOdczytowWody = new ArrayList<ReadingWater>();
//		listaNowychOdczytowWody.add(nowyOdczytGlownegoLicznikaWody);
//		listaNowychOdczytowWody.add(nowyOdczytWodyZimnejM1);
//		listaNowychOdczytowWody.add(nowyOdczytWodyCieplejM1);
//		listaNowychOdczytowWody.add(nowyOdczytWodyZimnejM2);
//		listaNowychOdczytowWody.add(nowyOdczytWodyCieplejM2);
//		listaNowychOdczytowWody.add(nowyOdczytWodyZimnejM3);
//		listaNowychOdczytowWody.add(nowyOdczytWodyCieplejM3);
//		listaNowychOdczytowWody.add(nowyOdczytWodyZimnejM4);
//		listaNowychOdczytowWody.add(nowyOdczytWodyCieplejM4);
//
////		OdczytWody NowyOdczytWodyCzescAdministracyjna = ManagerWody
////				.wyliczOdczytWodyCzesciAdministracyjnej(licznikWodaCzescWspolna, listaNowychOdczytowWody);
////		listaNowychOdczytowWody.add(NowyOdczytWodyCzescAdministracyjna);
//
//		// // Dane dotyczace
//		// Gazu--------------------------------------------------
//
//		MeterGas licznikGlownyGazu = new MeterGas("Glowny Licznik gazu", "rt", "kWh", null, false);
//		MeterGas licznikGazuM1 = new MeterGas("Licznik gaz M1", "4566", "kWh", listaMieszkan.get(1), false);
//		MeterGas licznikGazuM2 = new MeterGas("Licznik gaz M2", "4566", "kWh", listaMieszkan.get(2), false);
//		MeterGas licznikGazuM3 = new MeterGas("Licznik gaz M3", "4566", "kWh", listaMieszkan.get(3), false);
//		MeterGas licznikGazuM4 = new MeterGas("Licznik gaz M4", "4566", "kWh", listaMieszkan.get(4), false);
//		MeterGas licznikGazuPodlogaWspolna = new MeterGas("Licznik gaz czesc wspolna- podloga", "4566", "kWh",
//				listaMieszkan.get(0), false);
//		MeterGas licznikGazuKaloryferWspolny = new MeterGas("Licznik gazu kaloryfera czesci wspolnej", "fdsf",
//				"kWh", listaMieszkan.get(0), false);
//		MeterGas licznikGazuCWU = new MeterGas("Licznik CWU", "4555", "kWh", null, true);
//
//		ArrayList<MeterGas> listaLicznikowGazu = new ArrayList<MeterGas>();
//		listaLicznikowGazu.add(licznikGlownyGazu);
//		listaLicznikowGazu.add(licznikGazuM1);
//		listaLicznikowGazu.add(licznikGazuM2);
//		listaLicznikowGazu.add(licznikGazuM3);
//		listaLicznikowGazu.add(licznikGazuM4);
//		listaLicznikowGazu.add(licznikGazuPodlogaWspolna);
//		listaLicznikowGazu.add(licznikGazuKaloryferWspolny);
//		listaLicznikowGazu.add(licznikGazuCWU);
//
//		ReadingGas poprzedniOdczytGlownegoLicznikaGazu = new ReadingGas(format.parse("2015-01-17"), 0,
//				licznikGlownyGazu);
//		ReadingGas poprzedniOdczytLicznikaGazuM1 = new ReadingGas(format.parse("2015-01-17"), 0, licznikGazuM1);
//		ReadingGas poprzedniOdczytLicznikaGazuM2 = new ReadingGas(format.parse("2015-01-17"), 0, licznikGazuM2);
//		ReadingGas poprzedniOdczytLicznikaGazuM3 = new ReadingGas(format.parse("2015-01-17"), 0, licznikGazuM3);
//		ReadingGas poprzedniOdczytLicznikaGazuM4 = new ReadingGas(format.parse("2015-01-17"), 0, licznikGazuM4);
//		ReadingGas poprzedniOdczytLicznikaGazuPodlogaWspolna = new ReadingGas(format.parse("2015-01-17"), 0,
//				licznikGazuPodlogaWspolna);
//		ReadingGas poprzedniOdczytLicznikaGazuKaloryferWspolny = new ReadingGas(format.parse("2015-01-17"), 0,
//				licznikGazuKaloryferWspolny);
//		ReadingGas poprzedniOdczytLicznikaGazuCWU = new ReadingGas(format.parse("2015-01-17"), 0, licznikGazuCWU);
//
//		ArrayList<ReadingGas> listaPoprzednichOdczytowGazu = new ArrayList<ReadingGas>();
//		listaPoprzednichOdczytowGazu.add(poprzedniOdczytGlownegoLicznikaGazu);
//		listaPoprzednichOdczytowGazu.add(poprzedniOdczytLicznikaGazuM1);
//		listaPoprzednichOdczytowGazu.add(poprzedniOdczytLicznikaGazuM2);
//		listaPoprzednichOdczytowGazu.add(poprzedniOdczytLicznikaGazuM3);
//		listaPoprzednichOdczytowGazu.add(poprzedniOdczytLicznikaGazuM4);
//		listaPoprzednichOdczytowGazu.add(poprzedniOdczytLicznikaGazuPodlogaWspolna);
//		listaPoprzednichOdczytowGazu.add(poprzedniOdczytLicznikaGazuKaloryferWspolny);
//		listaPoprzednichOdczytowGazu.add(poprzedniOdczytLicznikaGazuCWU);
//
//		ReadingGas nowyOdczytGlownegoLicznikaGazu = new ReadingGas(new Date(), 623, licznikGlownyGazu);
//		ReadingGas nowyOdczytLicznikaGazuCWU = new ReadingGas(new Date(), 300, licznikGazuCWU);
//		ReadingGas nowyOdczytLicznikaGazuM1 = new ReadingGas(new Date(), 3, licznikGazuM1);
//		ReadingGas nowyOdczytLicznikaGazuM2 = new ReadingGas(new Date(), 20, licznikGazuM2);
//		ReadingGas nowyOdczytLicznikaGazuM3 = new ReadingGas(new Date(), 20, licznikGazuM3);
//		ReadingGas nowyOdczytLicznikaGazuM4 = new ReadingGas(new Date(), 20, licznikGazuM4);
//		ReadingGas nowyOdczytLicznikaGazuPodlogaWspolna = new ReadingGas(new Date(), 20, licznikGazuPodlogaWspolna);
//		ReadingGas nowyOdczytLicznikaGazuKaloryferWspolny = new ReadingGas(new Date(), 20, licznikGazuKaloryferWspolny);
//
//		ArrayList<ReadingGas> listaNowychOdczytowGazu = new ArrayList<ReadingGas>();
//		listaNowychOdczytowGazu.add(nowyOdczytGlownegoLicznikaGazu);
//		listaNowychOdczytowGazu.add(nowyOdczytLicznikaGazuM1);
//		listaNowychOdczytowGazu.add(nowyOdczytLicznikaGazuM2);
//		listaNowychOdczytowGazu.add(nowyOdczytLicznikaGazuM3);
//		listaNowychOdczytowGazu.add(nowyOdczytLicznikaGazuM4);
//		listaNowychOdczytowGazu.add(nowyOdczytLicznikaGazuPodlogaWspolna);
//		listaNowychOdczytowGazu.add(nowyOdczytLicznikaGazuKaloryferWspolny);
//		listaNowychOdczytowGazu.add(nowyOdczytLicznikaGazuCWU);
//
//		// -----------------------Macierze_zuzycia-----------------------------------------------------------------
//
//		ArrayList<UsageValue> listaWartosciZucyiaWoduDlaElementow = new ArrayList<UsageValue>();
//		listaWartosciZucyiaWoduDlaElementow = ManagerWater.countWaterConsumption(
//				listaMieszkan, listaPoprzednichOdczytowWody, listaNowychOdczytowWody);
//
//		ArrayList<UsageValue> listaWartoscZuzyciaEnergiiDlaElementow = new ArrayList<UsageValue>();
//		listaWartoscZuzyciaEnergiiDlaElementow = ManagerEnergy
//				.countEnergyConsupmtion(listaMieszkan, poprzednieOdczytyEnergii,
//						noweOdczytyEnergii);
//
//		ArrayList<UsageValue> listaWartoscZuzyciaGazuDlaElementow = new ArrayList<UsageValue>();
//		listaWartoscZuzyciaGazuDlaElementow = ManagerGas.countGasConsumption(
//				listaMieszkan, listaPoprzednichOdczytowGazu, listaNowychOdczytowGazu, listaPoprzednichOdczytowWody,
//				listaNowychOdczytowWody);
//
//		System.out.println("ENEGRIA");
//		System.out.println(listaWartoscZuzyciaEnergiiDlaElementow.toString());
//		System.out.println("WODA");
//		System.out.println(listaWartosciZucyiaWoduDlaElementow.toString());
//		System.out.println("GAZ");
//		System.out.println(listaWartoscZuzyciaGazuDlaElementow.toString());
//
//		// ----------------faktury-----------------------------------------------------------------
//
//		Invoice fakturaZaGaz = new InvoiceGas("345667", "faktura za gaz", new Date(), 120);
//		Invoice fakturaZaWode = new InvoiceWater("487", "faktura za wode", new Date(), 100);
//		Invoice fakturaZaEnergie = new InvoiceGas("4634", "faktura za energie", new Date(), 110);
//
//		HashMap<String, Invoice> mapaFaktur = new HashMap<String, Invoice>();
//		mapaFaktur.put("Energia", fakturaZaEnergie);
//		mapaFaktur.put("Woda", fakturaZaWode);
//		mapaFaktur.put("Gaz", fakturaZaGaz);
//
//		InvoiceGas fakturaGaz =new  InvoiceGas();
//		fakturaGaz.setDate(new Date());
//		fakturaGaz.setSerialNumber("456544");
//		fakturaGaz.setDescription("test");
//		fakturaGaz.setTotalAmount(130);
//	
//		// -------------------------------OPLATY-------------------------------------------------------------
//
//	
//		if (DivisionValidator.validateDivisionForPaymentController(listaMieszkan, algorytmPodzialuTmp, listaNajemcow) == true) {
////			ManagerOplat managerOplat = new ManagerOplat();
////
////			listaOplat2 = managerOplat.generujListeOplatDlaMieszkancow(listaNajemcow, mapaFaktur, algorytmPodzialuTmp,
////					listaWartoscZuzyciaEnergiiDlaElementow, listaWartosciZucyiaWoduDlaElementow,
////					listaWartoscZuzyciaGazuDlaElementow);
//
//		}
//		ArrayList<PaymentGas> oplataGaz = new ArrayList<>();
////		oplataGaz = ManagerPayment.createPaymentGasList(listaNajemcow, (InvoiceGas) mapaFaktur.get("Gaz"), algorytmPodzialuTmp,
////				listaWartoscZuzyciaGazuDlaElementow, new Date());
////		System.out.println(oplataGaz.toString());
////		for(PaymentGas kk:oplataGaz) {
////			PdfGenerator.generujOplate(kk);
////		}


		
		
		
	}
}