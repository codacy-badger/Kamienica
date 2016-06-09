package kamienica.testsetup;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.division.Division;
import kamienica.feature.invoice.InvoiceEnergy;
import kamienica.feature.invoice.InvoiceGas;
import kamienica.feature.invoice.InvoiceWater;
import kamienica.feature.meter.MeterEnergy;
import kamienica.feature.meter.MeterGas;
import kamienica.feature.meter.MeterWater;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingWater;
import kamienica.feature.tenant.Tenant;
import kamienica.feature.tenant.UserRole;
import kamienica.feature.tenant.UserStatus;
import kamienica.feature.usagevalue.UsageValue;

public class SetupMethods {

	public static ArrayList<Apartment> getApartmentList() {
		Apartment apartment0 = new Apartment(1, 0, "0000", "Czesc Wspolna");
		Apartment apartment1 = new Apartment(2, 1, "1111", "Piwnica");
		Apartment apartment2 = new Apartment(3, 2, "2222", "Parter");
		Apartment apartment3 = new Apartment(4, 3, "3333", "1 pietro");

		ArrayList<Apartment> apartments = new ArrayList<Apartment>();
		apartments.add(apartment0);
		apartments.add(apartment1);
		apartments.add(apartment2);
		apartments.add(apartment3);
		return apartments;
	}

	public static ArrayList<Tenant> getTenantList(List<Apartment> apartments) {

		Tenant tenant2 = new Tenant("Maciej (Admin)", "Fol", "kowalski@wp.pl", "222222", apartments.get(1));
		tenant2.setStatus(UserStatus.ACTIVE.getUserStatus());
		tenant2.setId(1);
		tenant2.setRole(UserRole.ADMIN.getUserRole());
		Tenant tenant3 = new Tenant("Adam", "Nowak", "nowak@wp.pl", "111111", apartments.get(2));
		tenant3.setStatus(UserStatus.ACTIVE.getUserStatus());
		tenant3.setId(2);

		ArrayList<Tenant> tenants = new ArrayList<Tenant>();
		tenants.add(tenant2);
		tenants.add(tenant3);
		return tenants;
	}

	public static ArrayList<Division> getDivisionList(List<Apartment> apartments, List<Tenant> tenants) {
		ArrayList<Division> division = new ArrayList<>();
		division.add(new Division(1, new Date(), tenants.get(0), apartments.get(0), 0.5));
		division.add(new Division(2, new Date(), tenants.get(0), apartments.get(1), 1));
		division.add(new Division(3, new Date(), tenants.get(0), apartments.get(2), 0));
		division.add(new Division(4, new Date(), tenants.get(0), apartments.get(3), 1));

		division.add(new Division(6, new Date(), tenants.get(1), apartments.get(0), 0.5));
		division.add(new Division(7, new Date(), tenants.get(1), apartments.get(1), 0));
		division.add(new Division(8, new Date(), tenants.get(1), apartments.get(2), 1));
		division.add(new Division(9, new Date(), tenants.get(1), apartments.get(3), 0));

		return division;
	}

	DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

	// ------------------------------LICZNIKI--------------------------------------------------------------------
	public static List<MeterEnergy> getMetersEnergy(List<Apartment> apartments) {
		List<MeterEnergy> metersEnergy = new ArrayList<>();
		MeterEnergy main = new MeterEnergy("Glowny Licznik energii", "354", "kWh", null);
		MeterEnergy M0 = new MeterEnergy("Licznik czesci administracyjnej", "89/0", "kWh", apartments.get(0));
		MeterEnergy M1 = new MeterEnergy("Licznik Energii M1", "89/0", "kWh", apartments.get(1));
		MeterEnergy M2 = new MeterEnergy("Licznik Energii M2", "89/0", "kWh", apartments.get(2));
		MeterEnergy M3 = new MeterEnergy("Licznik Energii M3", "89/0", "kWh", apartments.get(3));

		metersEnergy.add(M0);
		metersEnergy.add(M1);
		metersEnergy.add(M2);
		metersEnergy.add(M3);
		metersEnergy.add(main);
		return metersEnergy;
	}

	public static List<MeterWater> getMetersWater(List<Apartment> apartments) {
		List<MeterWater> metersWater = new ArrayList<>();
		MeterWater main = new MeterWater("Licznik woda glowny", "55", "m3", null, false);
		MeterWater M1cold = new MeterWater("woda zimna m1", "434", "metry3", apartments.get(1), false);
		MeterWater M1hot = new MeterWater("woda ciepla m1", "3455", "metry3", apartments.get(1), true);
		MeterWater M2cold = new MeterWater("woda zimna m2", "434", "metry3", apartments.get(2), false);
		MeterWater M2hot = new MeterWater("woda ciepla m2", "3455", "metry3", apartments.get(2), true);
		MeterWater M3cold = new MeterWater("woda zimna m3", "434", "metry3", apartments.get(3), false);
		MeterWater M3hot = new MeterWater("woda ciepla m3", "3455", "metry3", apartments.get(3), true);

		metersWater.add(M1cold);
		metersWater.add(M1hot);
		metersWater.add(M2cold);
		metersWater.add(M2hot);
		metersWater.add(M3cold);
		metersWater.add(M3hot);
		metersWater.add(main);
		return metersWater;
	}

	public static List<ReadingWater> getReadingsWaterOld(List<MeterWater> meters) {
		List<ReadingWater> readings = new ArrayList<>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			readings.add(new ReadingWater(format.parse("2015-02-01"), 80, meters.get(6)));
			readings.add(new ReadingWater(format.parse("2015-02-01"), 10, meters.get(0)));
			readings.add(new ReadingWater(format.parse("2015-02-01"), 10, meters.get(1)));
			readings.add(new ReadingWater(format.parse("2015-02-01"), 15, meters.get(2)));
			readings.add(new ReadingWater(format.parse("2015-02-01"), 15, meters.get(3)));
			readings.add(new ReadingWater(format.parse("2015-02-01"), 12, meters.get(4)));
			readings.add(new ReadingWater(format.parse("2015-02-01"), 12, meters.get(5)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return readings;
	}

	public static List<ReadingWater> getReadingsWaterNew(List<MeterWater> meters) {
		List<ReadingWater> readings = new ArrayList<>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			readings.add(new ReadingWater(format.parse("2015-03-01"), 100, meters.get(6)));
			readings.add(new ReadingWater(format.parse("2015-03-01"), 11, meters.get(0)));
			readings.add(new ReadingWater(format.parse("2015-03-01"), 11, meters.get(1)));
			readings.add(new ReadingWater(format.parse("2015-03-01"), 20, meters.get(2)));
			readings.add(new ReadingWater(format.parse("2015-03-01"), 20, meters.get(3)));
			readings.add(new ReadingWater(format.parse("2015-03-01"), 15, meters.get(4)));
			readings.add(new ReadingWater(format.parse("2015-03-01"), 15, meters.get(5)));
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return readings;
	}

	public static List<ReadingEnergy> getReadingsEnergyOld(List<MeterEnergy> meters) {
		List<ReadingEnergy> readingsEnergyOld = new ArrayList<>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {

			readingsEnergyOld.add(new ReadingEnergy(format.parse("2015-02-01"), 5, meters.get(0)));
			readingsEnergyOld.add(new ReadingEnergy(format.parse("2015-02-01"), 5, meters.get(1)));
			readingsEnergyOld.add(new ReadingEnergy(format.parse("2015-02-01"), 10, meters.get(2)));
			readingsEnergyOld.add(new ReadingEnergy(format.parse("2015-02-01"), 15, meters.get(3)));
			readingsEnergyOld.add(new ReadingEnergy(format.parse("2015-02-01"), 35, meters.get(4)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return readingsEnergyOld;
	}

	public static List<ReadingEnergy> getReadingsEnergyNew(List<MeterEnergy> meters) {
		List<ReadingEnergy> readingsEnergyNew = new ArrayList<>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {

			readingsEnergyNew.add(new ReadingEnergy(format.parse("2015-03-01"), 15, meters.get(0)));
			readingsEnergyNew.add(new ReadingEnergy(format.parse("2015-03-01"), 10, meters.get(1)));
			readingsEnergyNew.add(new ReadingEnergy(format.parse("2015-03-01"), 25, meters.get(2)));
			readingsEnergyNew.add(new ReadingEnergy(format.parse("2015-03-01"), 20, meters.get(3)));
			readingsEnergyNew.add(new ReadingEnergy(format.parse("2015-03-01"), 70, meters.get(4)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return readingsEnergyNew;
	}

	public static List<MeterGas> getMetersGas(List<Apartment> apartments) {
		List<MeterGas> metersGas = new ArrayList<>();
		metersGas.add(new MeterGas("Glowny Licznik gazu", "rt", "kWh", null, false));
		metersGas.add(new MeterGas("Licznik gaz czesc wspolna- podloga", "4566", "kWh", apartments.get(0), false));
		metersGas.add(new MeterGas("Licznik gazu kaloryfera czesci wspolnej", "fdsf", "kWh", apartments.get(0), false));
		metersGas.add(new MeterGas("Licznik gaz M1", "4566", "kWh", apartments.get(1), false));
		metersGas.add(new MeterGas("Licznik gaz M2", "4566", "kWh", apartments.get(2), false));
		metersGas.add(new MeterGas("Licznik gaz M3", "4566", "kWh", apartments.get(3), false));
		metersGas.add(new MeterGas("Licznik CWU", "4555", "kWh", null, true));
		return metersGas;
	}

	public static List<ReadingGas> getReadingsGasOld(List<MeterGas> meters) {
		List<ReadingGas> readingsGasOld = new ArrayList<>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			readingsGasOld.add(new ReadingGas(format.parse("2015-03-01"), 73, meters.get(0)));
			readingsGasOld.add(new ReadingGas(format.parse("2015-03-01"), 10, meters.get(1)));
			readingsGasOld.add(new ReadingGas(format.parse("2015-03-01"), 12, meters.get(2)));
			readingsGasOld.add(new ReadingGas(format.parse("2015-03-01"), 10, meters.get(3)));
			readingsGasOld.add(new ReadingGas(format.parse("2015-03-01"), 18, meters.get(4)));
			readingsGasOld.add(new ReadingGas(format.parse("2015-03-01"), 11, meters.get(5)));
			readingsGasOld.add(new ReadingGas(format.parse("2015-03-01"), 12, meters.get(6)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return readingsGasOld;
	}

	public static List<ReadingGas> getReadingsGasNew(List<MeterGas> meters) {
		List<ReadingGas> readingsGasNew = new ArrayList<>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {

			readingsGasNew.add(new ReadingGas(format.parse("2015-03-01"), 100, meters.get(0)));
			readingsGasNew.add(new ReadingGas(format.parse("2015-03-01"), 15, meters.get(1)));
			readingsGasNew.add(new ReadingGas(format.parse("2015-03-01"), 15, meters.get(2)));
			readingsGasNew.add(new ReadingGas(format.parse("2015-03-01"), 20, meters.get(3)));
			readingsGasNew.add(new ReadingGas(format.parse("2015-03-01"), 20, meters.get(4)));
			readingsGasNew.add(new ReadingGas(format.parse("2015-03-01"), 15, meters.get(5)));
			readingsGasNew.add(new ReadingGas(format.parse("2015-03-01"), 15, meters.get(6)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return readingsGasNew;
	}

	public static InvoiceEnergy getInvoiceEnergy(List<ReadingEnergy> newReadings) {
		return new InvoiceEnergy("23424", "energia", new Date(), 150, newReadings.get(0));
	}

	public static InvoiceWater getInvoiceWater(List<ReadingWater> newReadings) {
		return new InvoiceWater("23424", "energia", new Date(), 200, newReadings.get(0));
	}

	public static InvoiceGas getInvoiceGas(List<ReadingGas> newReadings) {
		return new InvoiceGas("23424", "energia", new Date(), 300, newReadings.get(0));
	}

	public static ArrayList<UsageValue> usageEnergy(List<Apartment> apartments) {
		ArrayList<UsageValue> out = new ArrayList<>();
		out.add(new UsageValue("czesc wspolna", 10, "kWh", 28, apartments.get(0)));
		out.add(new UsageValue("piwnica", 5, "kWh", 28, apartments.get(1)));
		out.add(new UsageValue("parter", 15, "kWh", 28, apartments.get(2)));
		out.add(new UsageValue("1 pieteo", 5, "kWh", 28, apartments.get(3)));
		return out;
	}

	public static ArrayList<UsageValue> usageWater(List<Apartment> apartments) {
		ArrayList<UsageValue> out = new ArrayList<>();
		out.add(new UsageValue("czesc wspolna", 2, "kWh", 28, apartments.get(0)));
		out.add(new UsageValue("piwnica", 2, "kWh", 28, apartments.get(1)));
		out.add(new UsageValue("parter", 10, "kWh", 28, apartments.get(2)));
		out.add(new UsageValue("1 pieteo", 6, "kWh", 28, apartments.get(3)));
		return out;
	}

	public static ArrayList<UsageValue> usageGas(List<Apartment> apartments) {
		ArrayList<UsageValue> out = new ArrayList<>();
		out.add(new UsageValue("czesc wspolna", 8, "kWh", 28, apartments.get(0)));
		out.add(new UsageValue("piwnica", 10.33, "kWh", 28, apartments.get(1)));
		out.add(new UsageValue("parter", 3.67, "kWh", 28, apartments.get(2)));
		out.add(new UsageValue("1 pieteo", 5, "kWh", 28, apartments.get(3)));
		return out;
	}

	// public static List<Apartment> getApartmentList() {
	// Apartment apartment0 = new Apartment(1, 0, "0000", "Czesc Wspolna");
	// Apartment apartment1 = new Apartment(2, 1, "1111", "Piwnica");
	// Apartment apartment2 = new Apartment(3, 2, "2222", "Parter");
	// Apartment apartment3 = new Apartment(4, 3, "3333", "1 pietro");
	//
	// ArrayList<Apartment> apartments = new ArrayList<Apartment>();
	// apartments.addAll(Arrays.asList(apartment0, apartment1, apartment2,
	// apartment3));
	// return apartments;
	// }
	//
	// public static List<Tenant> getTenantList(List<Apartment> apartments) {
	//// Tenant tenant1 = new Tenant("Nieaktywny ", "Fol", "kowalski@wp.pl",
	// "222222", apartments.get(1));
	//// tenant1.setStatus(UserStatus.INACTIVE.getUserStatus());
	// Tenant tenant2 = new Tenant("Maciej (Admin)", "Fol", "kowalski@wp.pl",
	// "222222", apartments.get(1));
	// tenant2.setStatus(UserStatus.ACTIVE.getUserStatus());
	// tenant2.setRole(UserRole.ADMIN.getUserRole());
	// Tenant tenant3 = new Tenant("Adam", "Nowak", "nowak@wp.pl", "111111",
	// apartments.get(2));
	// tenant3.setStatus(UserStatus.ACTIVE.getUserStatus());
	//
	// ArrayList<Tenant> tenants = new ArrayList<Tenant>();
	// tenants.add(tenant2);
	// tenants.add(tenant3);
	// return tenants;
	// }
	//
	// public static List<Division> getDivisionList(List<Apartment> apartments,
	// List<Tenant> tenants) {
	// List<Division> division = new ArrayList<>();
	// division.add(new Division(1, new Date(), tenants.get(0),
	// apartments.get(0), 0.5));
	// division.add(new Division(2, new Date(), tenants.get(0),
	// apartments.get(1), 1));
	// division.add(new Division(3, new Date(), tenants.get(0),
	// apartments.get(2), 0));
	// division.add(new Division(4, new Date(), tenants.get(0),
	// apartments.get(3), 1));
	//
	// division.add(new Division(6, new Date(), tenants.get(1),
	// apartments.get(0), 0.5));
	// division.add(new Division(7, new Date(), tenants.get(1),
	// apartments.get(1), 0));
	// division.add(new Division(8, new Date(), tenants.get(1),
	// apartments.get(2), 1));
	// division.add(new Division(9, new Date(), tenants.get(1),
	// apartments.get(3), 0));
	//
	// return division;
	// }
	//
	// DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	//
	// //
	// ------------------------------LICZNIKI--------------------------------------------------------------------
	// public static List<MeterEnergy> getMetersEnergy(List<Apartment>
	// apartments) {
	// List<MeterEnergy> metersEnergy = new ArrayList<>();
	// MeterEnergy main = new MeterEnergy("Glowny Licznik energii", "354",
	// "kWh", null);
	// MeterEnergy M0 = new MeterEnergy("Licznik czesci administracyjnej",
	// "89/0", "kWh", apartments.get(0));
	// MeterEnergy M1 = new MeterEnergy("Licznik Energii M1", "89/0", "kWh",
	// apartments.get(1));
	// MeterEnergy M2 = new MeterEnergy("Licznik Energii M2", "89/0", "kWh",
	// apartments.get(2));
	// MeterEnergy M3 = new MeterEnergy("Licznik Energii M3", "89/0", "kWh",
	// apartments.get(3));
	//
	// metersEnergy.add(main);
	// metersEnergy.add(M1);
	// metersEnergy.add(M2);
	// metersEnergy.add(M3);
	// metersEnergy.add(M0);
	// return metersEnergy;
	// }
	//
	// public static List<MeterWater> getMetersWater(List<Apartment> apartments)
	// {
	// List<MeterWater> metersWater = new ArrayList<>();
	// MeterWater main = new MeterWater("Licznik woda glowny", "55", "m3", null,
	// false);
	// MeterWater M1cold = new MeterWater("woda zimna m1", "434", "metry3",
	// apartments.get(1), false);
	// MeterWater M1hot = new MeterWater("woda ciepla m1", "3455", "metry3",
	// apartments.get(1), true);
	// MeterWater M2cold = new MeterWater("woda zimna m2", "434", "metry3",
	// apartments.get(2), false);
	// MeterWater M2hot = new MeterWater("woda ciepla m2", "3455", "metry3",
	// apartments.get(2), true);
	// MeterWater M3cold = new MeterWater("woda zimna m3", "434", "metry3",
	// apartments.get(3), false);
	// MeterWater M3hot = new MeterWater("woda ciepla m3", "3455", "metry3",
	// apartments.get(3), true);
	//
	// metersWater.add(main);
	// metersWater.add(M1cold);
	// metersWater.add(M1hot);
	// metersWater.add(M2cold);
	// metersWater.add(M2hot);
	// metersWater.add(M3cold);
	// metersWater.add(M3hot);
	// return metersWater;
	// }
	//
	// public static List<MeterGas> getMetersGas(List<Apartment> apartments) {
	// List<MeterGas> metersGas = new ArrayList<>();
	// metersGas.add(new MeterGas("Glowny Licznik gazu", "rt", "kWh", null,
	// false));
	// metersGas.add(new MeterGas("Licznik gaz czesc wspolna- podloga", "4566",
	// "kWh", apartments.get(0), false));
	// metersGas.add(new MeterGas("Licznik gazu kaloryfera czesci wspolnej",
	// "fdsf", "kWh", apartments.get(0), false));
	// metersGas.add(new MeterGas("Licznik gaz M1", "4566", "kWh",
	// apartments.get(1), false));
	// metersGas.add(new MeterGas("Licznik gaz M2", "4566", "kWh",
	// apartments.get(2), false));
	// metersGas.add(new MeterGas("Licznik gaz M3", "4566", "kWh",
	// apartments.get(3), false));
	// metersGas.add(new MeterGas("Licznik CWU", "4555", "kWh", null, true));
	// return metersGas;
	// }
	//
	// public static List<ReadingWater> getReadingsWater(List<MeterWater>
	// meters) {
	// List<ReadingWater> readings = new ArrayList<>();
	// DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	// try {
	// readings.add(new ReadingWater(format.parse("2015-01-01"), 0,
	// meters.get(0)));
	// readings.add(new ReadingWater(format.parse("2015-01-01"), 0,
	// meters.get(1)));
	// readings.add(new ReadingWater(format.parse("2015-01-01"), 0,
	// meters.get(2)));
	// readings.add(new ReadingWater(format.parse("2015-01-01"), 0,
	// meters.get(3)));
	// readings.add(new ReadingWater(format.parse("2015-01-01"), 0,
	// meters.get(4)));
	// readings.add(new ReadingWater(format.parse("2015-01-01"), 0,
	// meters.get(5)));
	// readings.add(new ReadingWater(format.parse("2015-01-01"), 0,
	// meters.get(6)));
	//
	// readings.add(new ReadingWater(format.parse("2015-02-01"), 80,
	// meters.get(0)));
	// readings.add(new ReadingWater(format.parse("2015-02-01"), 10,
	// meters.get(1)));
	// readings.add(new ReadingWater(format.parse("2015-02-01"), 10,
	// meters.get(2)));
	// readings.add(new ReadingWater(format.parse("2015-02-01"), 15,
	// meters.get(3)));
	// readings.add(new ReadingWater(format.parse("2015-02-01"), 15,
	// meters.get(4)));
	// readings.add(new ReadingWater(format.parse("2015-02-01"), 12,
	// meters.get(5)));
	// readings.add(new ReadingWater(format.parse("2015-02-01"), 12,
	// meters.get(6)));
	//
	// readings.add(new ReadingWater(format.parse("2015-03-01"), 100,
	// meters.get(0)));
	// readings.add(new ReadingWater(format.parse("2015-03-01"), 11,
	// meters.get(1)));
	// readings.add(new ReadingWater(format.parse("2015-03-01"), 11,
	// meters.get(2)));
	// readings.add(new ReadingWater(format.parse("2015-03-01"), 20,
	// meters.get(3)));
	// readings.add(new ReadingWater(format.parse("2015-03-01"), 20,
	// meters.get(4)));
	// readings.add(new ReadingWater(format.parse("2015-03-01"), 15,
	// meters.get(5)));
	// readings.add(new ReadingWater(format.parse("2015-03-01"), 15,
	// meters.get(6)));
	// } catch (ParseException e) {
	//
	// e.printStackTrace();
	// }
	// return readings;
	// }
	//
	// public static List<ReadingEnergy> getReadingsEnergy(List<MeterEnergy>
	// meters) {
	// List<ReadingEnergy> readings = new ArrayList<>();
	// DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	// try {
	// readings.add(new ReadingEnergy(format.parse("2015-01-01"), 0,
	// meters.get(0)));
	// readings.add(new ReadingEnergy(format.parse("2015-01-01"), 0,
	// meters.get(1)));
	// readings.add(new ReadingEnergy(format.parse("2015-01-01"), 0,
	// meters.get(2)));
	// readings.add(new ReadingEnergy(format.parse("2015-01-01"), 0,
	// meters.get(3)));
	// readings.add(new ReadingEnergy(format.parse("2015-01-01"), 0,
	// meters.get(4)));
	//
	// readings.add(new ReadingEnergy(format.parse("2015-02-01"), 35,
	// meters.get(0)));
	// readings.add(new ReadingEnergy(format.parse("2015-02-01"), 5,
	// meters.get(1)));
	// readings.add(new ReadingEnergy(format.parse("2015-02-01"), 5,
	// meters.get(2)));
	// readings.add(new ReadingEnergy(format.parse("2015-02-01"), 10,
	// meters.get(3)));
	// readings.add(new ReadingEnergy(format.parse("2015-02-01"), 15,
	// meters.get(4)));
	//
	// readings.add(new ReadingEnergy(format.parse("2015-03-01"), 70,
	// meters.get(0)));
	// readings.add(new ReadingEnergy(format.parse("2015-03-01"), 15,
	// meters.get(1)));
	// readings.add(new ReadingEnergy(format.parse("2015-03-01"), 10,
	// meters.get(2)));
	// readings.add(new ReadingEnergy(format.parse("2015-03-01"), 25,
	// meters.get(3)));
	// readings.add(new ReadingEnergy(format.parse("2015-03-01"), 20,
	// meters.get(4)));
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// return readings;
	// }
	//
	// public static List<ReadingGas> getReadingsGas(List<MeterGas> meters) {
	// List<ReadingGas> readings = new ArrayList<>();
	// DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	// try {
	// readings.add(new ReadingGas(format.parse("2015-01-01"), 0,
	// meters.get(0)));
	// readings.add(new ReadingGas(format.parse("2015-01-01"), 0,
	// meters.get(1)));
	// readings.add(new ReadingGas(format.parse("2015-01-01"), 0,
	// meters.get(2)));
	// readings.add(new ReadingGas(format.parse("2015-01-01"), 0,
	// meters.get(3)));
	// readings.add(new ReadingGas(format.parse("2015-01-01"), 0,
	// meters.get(4)));
	// readings.add(new ReadingGas(format.parse("2015-01-01"), 0,
	// meters.get(5)));
	// readings.add(new ReadingGas(format.parse("2015-01-01"), 0,
	// meters.get(6)));
	//
	// readings.add(new ReadingGas(format.parse("2015-02-01"), 78,
	// meters.get(0)));
	// readings.add(new ReadingGas(format.parse("2015-02-01"), 10,
	// meters.get(1)));
	// readings.add(new ReadingGas(format.parse("2015-02-01"), 10,
	// meters.get(2)));
	// readings.add(new ReadingGas(format.parse("2015-02-01"), 15,
	// meters.get(3)));
	// readings.add(new ReadingGas(format.parse("2015-02-01"), 15,
	// meters.get(4)));
	// readings.add(new ReadingGas(format.parse("2015-02-01"), 14,
	// meters.get(5)));
	// readings.add(new ReadingGas(format.parse("2015-02-01"), 14,
	// meters.get(6)));
	//
	// readings.add(new ReadingGas(format.parse("2015-03-01"), 100,
	// meters.get(0)));
	// readings.add(new ReadingGas(format.parse("2015-03-01"), 15,
	// meters.get(1)));
	// readings.add(new ReadingGas(format.parse("2015-03-01"), 15,
	// meters.get(2)));
	// readings.add(new ReadingGas(format.parse("2015-03-01"), 20,
	// meters.get(3)));
	// readings.add(new ReadingGas(format.parse("2015-03-01"), 20,
	// meters.get(4)));
	// readings.add(new ReadingGas(format.parse("2015-03-01"), 15,
	// meters.get(5)));
	// readings.add(new ReadingGas(format.parse("2015-03-01"), 15,
	// meters.get(6)));
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// return readings;
	// }
	//
	// public static List<InvoiceEnergy> getInvoicesEnergy() {
	// List<InvoiceEnergy> list = new ArrayList<>();
	// DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	//// try {
	//// list.add(new InvoiceEnergy("12", "1", format.parse("2015-01-01"),
	// 200));
	//// list.add(new InvoiceEnergy("12", "2", format.parse("2015-02-01"),
	// 200));
	//// } catch (ParseException e) {
	//// e.printStackTrace();
	//// }
	// return list;
	// }
	//
	//// public static List<InvoiceGas> getInvoicesGas() {
	//// List<InvoiceGas> list = new ArrayList<>();
	//// DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	//// try {
	//// list.add(new InvoiceGas("12", "1", format.parse("2015-01-01"), 200));
	//// list.add(new InvoiceGas("12", "2", format.parse("2015-02-01"), 200));
	//// } catch (ParseException e) {
	//// e.printStackTrace();
	//// }
	//// return list;
	//// }
	////
	//// public static List<InvoiceWater> getInvoicesWater() {
	//// List<InvoiceWater> list = new ArrayList<>();
	//// DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	//// try {
	//// list.add(new InvoiceWater("12", "1", format.parse("2015-01-01"), 200));
	//// list.add(new InvoiceWater("12", "2", format.parse("2015-02-01"), 200));
	//// } catch (ParseException e) {
	//// e.printStackTrace();
	//// }
	//// return list;
	//// }
	//
	//// public static List<PaymentWater> getPaymentsWater(List<InvoiceWater>
	// invoices, List<Tenant> tenants)
	//// throws ParseException {
	//// List<PaymentWater> list = new ArrayList<>();
	//// DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	//// list.add(new PaymentWater(1, format.parse("2015-01-01"), 117.5,
	// tenants.get(0), invoices.get(0)));
	//// list.add(new PaymentWater(1, format.parse("2015-01-01"), 82.5,
	// tenants.get(1), invoices.get(0)));
	//// return list;
	//// }
	////
	//// public static List<PaymentGas> getPaymentsGas(List<InvoiceGas>
	// invoices, List<Tenant> tenants)
	//// throws ParseException {
	//// List<PaymentGas> list = new ArrayList<>();
	//// DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	//// list.add(new PaymentGas(1, format.parse("2015-01-01"), 121.3444213,
	// tenants.get(0), invoices.get(0)));
	//// list.add(new PaymentGas(1, format.parse("2015-01-01"), 78.65557866,
	// tenants.get(1), invoices.get(0)));
	//// return list;
	//// }
	////
	//// public static List<PaymentEnergy> getPaymentsEnergy(List<InvoiceEnergy>
	// invoices, List<Tenant> tenants)
	//// throws ParseException {
	//// List<PaymentEnergy> list = new ArrayList<>();
	//// DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	//// list.add(new PaymentEnergy(1, format.parse("2015-01-01"), 128.5714286,
	// tenants.get(0),
	//// invoices.get(0)));
	//// list.add(new PaymentEnergy(1, format.parse("2015-01-01"), 71.42857143,
	// tenants.get(1),
	//// invoices.get(0)));
	//// return list;
	//// }
}
