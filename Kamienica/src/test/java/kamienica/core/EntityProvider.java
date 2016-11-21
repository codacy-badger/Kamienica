package kamienica.core;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import kamienica.core.util.Status;
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

public class EntityProvider {

	public static final List<Apartment> APARTMENTS = getApartmentList();
	public static final List<Tenant> TENANTS = getTenantList();
	
	public static final List<MeterEnergy> METERS_ENERGY = getMetersEnergy();
	public static final List<MeterWater> METERS_WATER = getMeterWater();
	public static final List<MeterGas> METERS_GAS = getMeterGas();
	
	public static final List<ReadingEnergy> ENERGY_OLD = getReadingsEnergyOld();
	public static final List<ReadingEnergy> ENERGY_NEW = getReadingsEnergyNew();
	
	// --------------------------------------SETUP--------------------------------------------------------------
	public static List<Apartment> getApartmentList() {
		Apartment apartment0 = new Apartment(1L, 0, "0000", "Czesc Wspolna");
		Apartment apartment1 = new Apartment(2L, 1, "1111", "Piwnica");
		Apartment apartment2 = new Apartment(3L, 2, "2222", "Parter");
		Apartment apartment3 = new Apartment(4L, 3, "3333", "1 pietro");

		List<Apartment> apartments = new ArrayList<Apartment>();
		apartments.add(apartment0);
		apartments.add(apartment1);
		apartments.add(apartment2);
		apartments.add(apartment3);
		return apartments;
	}

	public static List<Tenant> getTenantList() {

		Tenant tenant2 = new Tenant("Maciej (Admin)", "Fol", "kowalski@wp.pl", "222222", APARTMENTS.get(1));
		tenant2.setStatus(Status.ACTIVE.getStatus());
		tenant2.setId(1L);
		tenant2.setRole(UserRole.ADMIN.getUserRole());
		Tenant tenant3 = new Tenant("Adam", "Nowak", "nowak@wp.pl", "111111", APARTMENTS.get(2));
		tenant3.setStatus(Status.ACTIVE.getStatus());
		tenant3.setId(2L);

		List<Tenant> tenants = new ArrayList<Tenant>();
		tenants.add(tenant2);
		tenants.add(tenant3);
		return tenants;
	}

	public static List<Division> getDivisionList() {
		ArrayList<Division> division = new ArrayList<>();
		division.add(new Division(1L, new LocalDate(), TENANTS.get(0), APARTMENTS.get(0), 0.5));
		division.add(new Division(2L, new LocalDate(), TENANTS.get(0), APARTMENTS.get(1), 1));
		division.add(new Division(3L, new LocalDate(), TENANTS.get(0), APARTMENTS.get(2), 0));
		division.add(new Division(4L, new LocalDate(), TENANTS.get(0), APARTMENTS.get(3), 1));

		division.add(new Division(6L, new LocalDate(), TENANTS.get(1), APARTMENTS.get(0), 0.5));
		division.add(new Division(7L, new LocalDate(), TENANTS.get(1), APARTMENTS.get(1), 0));
		division.add(new Division(8L, new LocalDate(), TENANTS.get(1), APARTMENTS.get(2), 1));
		division.add(new Division(9L, new LocalDate(), TENANTS.get(1), APARTMENTS.get(3), 0));

		return division;
	}


	// ------------------------------LICZNIKI--------------------------------------------------------------------
	public static List<MeterEnergy> getMetersEnergy() {
		List<MeterEnergy> metersEnergy = new ArrayList<>();
		MeterEnergy main = new MeterEnergy("Glowny Licznik energii", "354", "kWh", null);
		MeterEnergy M0 = new MeterEnergy("Licznik czesci administracyjnej", "89/0", "kWh", APARTMENTS.get(0));
		MeterEnergy M1 = new MeterEnergy("Licznik Energii M1", "89/0", "kWh", APARTMENTS.get(1));
		MeterEnergy M2 = new MeterEnergy("Licznik Energii M2", "89/0", "kWh", APARTMENTS.get(2));
		MeterEnergy M3 = new MeterEnergy("Licznik Energii M3", "89/0", "kWh", APARTMENTS.get(3));

		metersEnergy.add(M0);
		metersEnergy.add(M1);
		metersEnergy.add(M2);
		metersEnergy.add(M3);
		metersEnergy.add(main);
		return metersEnergy;
	}
	
	public static List<MeterGas> getMeterGas() {
		List<MeterGas> metersGas = new ArrayList<>();
		metersGas.add(new MeterGas("Glowny Licznik gazu", "rt", "kWh", null, false));
		metersGas.add(new MeterGas("Licznik gaz czesc wspolna- podloga", "4566", "kWh", APARTMENTS.get(0), false));
		metersGas.add(new MeterGas("Licznik gazu kaloryfera czesci wspolnej", "fdsf", "kWh", APARTMENTS.get(0), false));
		metersGas.add(new MeterGas("Licznik gaz M1", "4566", "kWh", APARTMENTS.get(1), false));
		metersGas.add(new MeterGas("Licznik gaz M2", "4566", "kWh", APARTMENTS.get(2), false));
		metersGas.add(new MeterGas("Licznik gaz M3", "4566", "kWh", APARTMENTS.get(3), false));
		metersGas.add(new MeterGas("Licznik CWU", "4555", "kWh", null, true));
		return metersGas;
	}

	public static List<MeterWater> getMeterWater() {
		List<MeterWater> metersWater = new ArrayList<>();
		MeterWater main = new MeterWater("Licznik woda glowny", "55", "m3", null, false);
		MeterWater M1cold = new MeterWater("woda zimna m1", "434", "metry3", APARTMENTS.get(1), false);
		MeterWater M1hot = new MeterWater("woda ciepla m1", "3455", "metry3", APARTMENTS.get(1), true);
		MeterWater M2cold = new MeterWater("woda zimna m2", "434", "metry3", APARTMENTS.get(2), false);
		MeterWater M2hot = new MeterWater("woda ciepla m2", "3455", "metry3", APARTMENTS.get(2), true);
		MeterWater M3cold = new MeterWater("woda zimna m3", "434", "metry3", APARTMENTS.get(3), false);
		MeterWater M3hot = new MeterWater("woda ciepla m3", "3455", "metry3", APARTMENTS.get(3), true);

		metersWater.add(M1cold);
		metersWater.add(M1hot);
		metersWater.add(M2cold);
		metersWater.add(M2hot);
		metersWater.add(M3cold);
		metersWater.add(M3hot);
		metersWater.add(main);
		return metersWater;
	}

	
	//
	public static List<ReadingWater> getReadingsWaterOld() {
		List<ReadingWater> readings = new ArrayList<>();
		readings.add(new ReadingWater(LocalDate.parse("2015-02-01"), 80, METERS_WATER.get(6)));
		readings.add(new ReadingWater(LocalDate.parse("2015-02-01"), 10, METERS_WATER.get(0)));
		readings.add(new ReadingWater(LocalDate.parse("2015-02-01"), 10, METERS_WATER.get(1)));
		readings.add(new ReadingWater(LocalDate.parse("2015-02-01"), 15, METERS_WATER.get(2)));
		readings.add(new ReadingWater(LocalDate.parse("2015-02-01"), 15, METERS_WATER.get(3)));
		readings.add(new ReadingWater(LocalDate.parse("2015-02-01"), 12, METERS_WATER.get(4)));
		readings.add(new ReadingWater(LocalDate.parse("2015-02-01"), 12, METERS_WATER.get(5)));

		return readings;
	}

	public static List<ReadingWater> getReadingsWaterNew() {
		List<ReadingWater> readings = new ArrayList<>();
		readings.add(new ReadingWater(LocalDate.parse("2015-03-01"), 100, METERS_WATER.get(6)));
		readings.add(new ReadingWater(LocalDate.parse("2015-03-01"), 11, METERS_WATER.get(0)));
		readings.add(new ReadingWater(LocalDate.parse("2015-03-01"), 11, METERS_WATER.get(1)));
		readings.add(new ReadingWater(LocalDate.parse("2015-03-01"), 20, METERS_WATER.get(2)));
		readings.add(new ReadingWater(LocalDate.parse("2015-03-01"), 20, METERS_WATER.get(3)));
		readings.add(new ReadingWater(LocalDate.parse("2015-03-01"), 15, METERS_WATER.get(4)));
		readings.add(new ReadingWater(LocalDate.parse("2015-03-01"), 15, METERS_WATER.get(5)));
		return readings;
	}

	public static List<ReadingEnergy> getReadingsEnergyOld() {
		List<ReadingEnergy> readingsEnergyOld = new ArrayList<>();
		readingsEnergyOld.add(new ReadingEnergy(LocalDate.parse("2015-02-01"), 5, METERS_ENERGY.get(0)));
		readingsEnergyOld.add(new ReadingEnergy(LocalDate.parse("2015-02-01"), 5, METERS_ENERGY.get(1)));
		readingsEnergyOld.add(new ReadingEnergy(LocalDate.parse("2015-02-01"), 10, METERS_ENERGY.get(2)));
		readingsEnergyOld.add(new ReadingEnergy(LocalDate.parse("2015-02-01"), 15, METERS_ENERGY.get(3)));
		readingsEnergyOld.add(new ReadingEnergy(LocalDate.parse("2015-02-01"), 35, METERS_ENERGY.get(4)));
		return readingsEnergyOld;
	}

	public static List<ReadingEnergy> getReadingsEnergyNew() {
		List<ReadingEnergy> readingsEnergyNew = new ArrayList<>();
		readingsEnergyNew.add(new ReadingEnergy(LocalDate.parse("2015-03-01"), 15, METERS_ENERGY.get(0)));
		readingsEnergyNew.add(new ReadingEnergy(LocalDate.parse("2015-03-01"), 10, METERS_ENERGY.get(1)));
		readingsEnergyNew.add(new ReadingEnergy(LocalDate.parse("2015-03-01"), 25, METERS_ENERGY.get(2)));
		readingsEnergyNew.add(new ReadingEnergy(LocalDate.parse("2015-03-01"), 20, METERS_ENERGY.get(3)));
		readingsEnergyNew.add(new ReadingEnergy(LocalDate.parse("2015-03-01"), 70, METERS_ENERGY.get(4)));
		return readingsEnergyNew;
	}



	public static List<ReadingGas> getReadingsGasOld(List<MeterGas> meters) {
		List<ReadingGas> readingsGasOld = new ArrayList<>();
		readingsGasOld.add(new ReadingGas(LocalDate.parse("2015-03-01"), 73, meters.get(0)));
		readingsGasOld.add(new ReadingGas(LocalDate.parse("2015-03-01"), 10, meters.get(1)));
		readingsGasOld.add(new ReadingGas(LocalDate.parse("2015-03-01"), 12, meters.get(2)));
		readingsGasOld.add(new ReadingGas(LocalDate.parse("2015-03-01"), 10, meters.get(3)));
		readingsGasOld.add(new ReadingGas(LocalDate.parse("2015-03-01"), 18, meters.get(4)));
		readingsGasOld.add(new ReadingGas(LocalDate.parse("2015-03-01"), 11, meters.get(5)));
		readingsGasOld.add(new ReadingGas(LocalDate.parse("2015-03-01"), 12, meters.get(6)));
		return readingsGasOld;
	}

	public static List<ReadingGas> getReadingsGasNew(List<MeterGas> meters) {
		List<ReadingGas> readingsGasNew = new ArrayList<>();

		readingsGasNew.add(new ReadingGas(LocalDate.parse("2015-03-01"), 100, meters.get(0)));
		readingsGasNew.add(new ReadingGas(LocalDate.parse("2015-03-01"), 15, meters.get(1)));
		readingsGasNew.add(new ReadingGas(LocalDate.parse("2015-03-01"), 15, meters.get(2)));
		readingsGasNew.add(new ReadingGas(LocalDate.parse("2015-03-01"), 20, meters.get(3)));
		readingsGasNew.add(new ReadingGas(LocalDate.parse("2015-03-01"), 20, meters.get(4)));
		readingsGasNew.add(new ReadingGas(LocalDate.parse("2015-03-01"), 15, meters.get(5)));
		readingsGasNew.add(new ReadingGas(LocalDate.parse("2015-03-01"), 15, meters.get(6)));

		return readingsGasNew;
	}

	public static InvoiceEnergy getInvoiceEnergy(List<ReadingEnergy> newReadings) {
		return new InvoiceEnergy("23424", "energia", new LocalDate(), 150, newReadings.get(0));
	}

	public static InvoiceWater getInvoiceWater(List<ReadingWater> newReadings) {
		return new InvoiceWater("23424", "energia", new LocalDate(), 200, newReadings.get(0));
	}

	public static InvoiceGas getInvoiceGas(List<ReadingGas> newReadings) {
		return new InvoiceGas("23424", "energia", new LocalDate(), 300, newReadings.get(0));
	}
}
