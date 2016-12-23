package kamienica.testutils;

import kamienica.core.enums.Status;
import kamienica.core.enums.UserRole;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingWater;
import kamienica.model.*;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class EntityProviderForDB {

    private static final LocalDate FEBRUARY = LocalDate.parse("2015-02-01");
    private static final LocalDate MARCH = LocalDate.parse("2015-03-01");

    public static final List<Apartment> APARTMENTS = getApartmentList();
    public static final List<Tenant> TENANTS = getTenantList();

    public static final List<MeterEnergy> METERS_ENERGY = getMetersEnergy();
    public static final List<MeterWater> METERS_WATER = getMeterWater();
    public static final List<MeterGas> METERS_GAS = getMeterGas();

    public static final List<ReadingEnergy> ENERGY_OLD = getReadingsEnergyOld();
    public static final List<ReadingEnergy> ENERGY_NEW = getReadingsEnergyNew();
    public static final List<ReadingEnergy> ENERGY_NEW_MINUS = getReadingsEnergyNewForMinusResult();

    public static final List<ReadingGas> GAS_OLD = getReadingsGasOld();
    public static final List<ReadingGas> GAS_NEW = getReadingsGasNew();
    public static final List<ReadingWater> WATER_OLD = getReadingsWaterOld();
    public static final List<ReadingWater> WATER_NEW = getReadingsWaterNew();
    public static final List<Division> DIVISION = getDivisionList();
    public static final List<Division> DIVISION_WRONG = getWrongDivisionList();


    // --------------------------------------SETUP--------------------------------------------------------------
    private static List<Apartment> getApartmentList() {
        Apartment apartment0 = new Apartment(0, "0000", "Czesc Wspolna");
        Apartment apartment1 = new Apartment(1, "1111", "Piwnica");
        Apartment apartment2 = new Apartment(2, "2222", "Parter");
        Apartment apartment3 = new Apartment(3, "3333", "1 pietro");

        List<Apartment> apartments = new ArrayList<>();
        apartments.add(apartment0);
        apartments.add(apartment1);
        apartments.add(apartment2);
        apartments.add(apartment3);
        return apartments;
    }

    private static List<Tenant> getTenantList() {
        final List<Tenant> tenants = new ArrayList<>();
        Tenant tenant2 = new Tenant("Maciej (Admin)", "Fol", "kowalski@wp.pl", "222222", APARTMENTS.get(1));
        tenant2.setStatus(Status.ACTIVE);
        tenant2.setRole(UserRole.ADMIN);
        Tenant tenant3 = new Tenant("Adam", "Nowak", "nowak@wp.pl", "111111", APARTMENTS.get(2));
        tenant3.setStatus(Status.ACTIVE);


        tenants.add(tenant3);
        return tenants;
    }

    private static List<Division> getDivisionList() {
        ArrayList<Division> division = new ArrayList<>();
        division.add(new Division(new LocalDate(), TENANTS.get(0), APARTMENTS.get(0), 0.5));
        division.add(new Division(new LocalDate(), TENANTS.get(0), APARTMENTS.get(1), 1));
        division.add(new Division(new LocalDate(), TENANTS.get(0), APARTMENTS.get(2), 0));
        division.add(new Division(new LocalDate(), TENANTS.get(0), APARTMENTS.get(3), 1));

        division.add(new Division(6L, new LocalDate(), TENANTS.get(1), APARTMENTS.get(0), 0.5));
        division.add(new Division(7L, new LocalDate(), TENANTS.get(1), APARTMENTS.get(1), 0));
        division.add(new Division(8L, new LocalDate(), TENANTS.get(1), APARTMENTS.get(2), 1));
        division.add(new Division(9L, new LocalDate(), TENANTS.get(1), APARTMENTS.get(3), 0));

        return division;
    }

    private static List<Division> getWrongDivisionList() {
        List<Division> division = getDivisionList();
        division.get(3).setDivisionValue(4.0);
        return division;
    }

    // ------------------------------LICZNIKI--------------------------------------------------------------------


    private static List<MeterGas> getMeterGas() {
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


    //------------------------------ENERGY-------------------

    private static List<MeterEnergy> getMetersEnergy() {
        List<MeterEnergy> metersEnergy = new ArrayList<>();
        metersEnergy.add(new MeterEnergy("Glowny Licznik energii", "354", "kWh", null));
        metersEnergy.add(new MeterEnergy("Licznik czesci administracyjnej", "89/0", "kWh", APARTMENTS.get(0)));
        metersEnergy.add(new MeterEnergy("Licznik Energii M1", "89/0", "kWh", APARTMENTS.get(1)));
        metersEnergy.add(new MeterEnergy("Licznik Energii M2", "89/0", "kWh", APARTMENTS.get(2)));
        metersEnergy.add(new MeterEnergy("Licznik Energii M3", "89/0", "kWh", APARTMENTS.get(3)));
        return metersEnergy;
    }

    private static List<ReadingEnergy> getReadingsEnergyOld() {
        List<ReadingEnergy> readingsEnergyOld = new ArrayList<>();
        readingsEnergyOld.add(new ReadingEnergy(FEBRUARY, 5, METERS_ENERGY.get(0)));
        readingsEnergyOld.add(new ReadingEnergy(FEBRUARY, 5, METERS_ENERGY.get(1)));
        readingsEnergyOld.add(new ReadingEnergy(FEBRUARY, 10, METERS_ENERGY.get(2)));
        readingsEnergyOld.add(new ReadingEnergy(FEBRUARY, 15, METERS_ENERGY.get(3)));
        readingsEnergyOld.add(new ReadingEnergy(FEBRUARY, 35, METERS_ENERGY.get(4)));
        return readingsEnergyOld;
    }

    private static List<ReadingEnergy> getReadingsEnergyNew() {
        List<ReadingEnergy> readingsEnergyNew = new ArrayList<>();
        readingsEnergyNew.add(new ReadingEnergy(MARCH, 15, METERS_ENERGY.get(0)));
        readingsEnergyNew.add(new ReadingEnergy(MARCH, 10, METERS_ENERGY.get(1)));
        readingsEnergyNew.add(new ReadingEnergy(MARCH, 25, METERS_ENERGY.get(2)));
        readingsEnergyNew.add(new ReadingEnergy(MARCH, 20, METERS_ENERGY.get(3)));
        readingsEnergyNew.add(new ReadingEnergy(MARCH, 70, METERS_ENERGY.get(4)));
        return readingsEnergyNew;
    }

    private static List<ReadingEnergy> getReadingsEnergyNewForMinusResult() {
        List<ReadingEnergy> readingsEnergyNew = new ArrayList<>();
        readingsEnergyNew.add(new ReadingEnergy(MARCH, 1, METERS_ENERGY.get(0)));
        readingsEnergyNew.add(new ReadingEnergy(MARCH, 1, METERS_ENERGY.get(1)));
        readingsEnergyNew.add(new ReadingEnergy(MARCH, 2, METERS_ENERGY.get(2)));
        readingsEnergyNew.add(new ReadingEnergy(MARCH, 2, METERS_ENERGY.get(3)));
        readingsEnergyNew.add(new ReadingEnergy(MARCH, 7, METERS_ENERGY.get(4)));
        return readingsEnergyNew;
    }

    private static List<ReadingGas> getReadingsGasOld() {
        List<ReadingGas> readingsGasOld = new ArrayList<>();
        readingsGasOld.add(new ReadingGas(FEBRUARY, 73, METERS_GAS.get(0)));
        readingsGasOld.add(new ReadingGas(FEBRUARY, 10, METERS_GAS.get(1)));
        readingsGasOld.add(new ReadingGas(FEBRUARY, 12, METERS_GAS.get(2)));
        readingsGasOld.add(new ReadingGas(FEBRUARY, 10, METERS_GAS.get(3)));
        readingsGasOld.add(new ReadingGas(FEBRUARY, 18, METERS_GAS.get(4)));
        readingsGasOld.add(new ReadingGas(FEBRUARY, 11, METERS_GAS.get(5)));
        readingsGasOld.add(new ReadingGas(FEBRUARY, 12, METERS_GAS.get(6)));
        return readingsGasOld;
    }

    private static List<ReadingGas> getReadingsGasNew() {
        List<ReadingGas> readingsGasNew = new ArrayList<>();
        readingsGasNew.add(new ReadingGas(MARCH, 100, METERS_GAS.get(0)));
        readingsGasNew.add(new ReadingGas(MARCH, 15, METERS_GAS.get(1)));
        readingsGasNew.add(new ReadingGas(MARCH, 15, METERS_GAS.get(2)));
        readingsGasNew.add(new ReadingGas(MARCH, 20, METERS_GAS.get(3)));
        readingsGasNew.add(new ReadingGas(MARCH, 20, METERS_GAS.get(4)));
        readingsGasNew.add(new ReadingGas(MARCH, 15, METERS_GAS.get(5)));
        readingsGasNew.add(new ReadingGas(MARCH, 15, METERS_GAS.get(6)));
        return readingsGasNew;
    }

    //--------------------------------------WATER--------------------------
    private static List<MeterWater> getMeterWater() {
        List<MeterWater> metersWater = new ArrayList<>();
        metersWater.add(new MeterWater("Licznik woda glowny", "55", "m3", null, false));
        metersWater.add(new MeterWater("woda zimna m1", "434", "metry3", APARTMENTS.get(1), false));
        metersWater.add(new MeterWater("woda ciepla m1", "3455", "metry3", APARTMENTS.get(1), true));
        metersWater.add(new MeterWater("woda zimna m2", "434", "metry3", APARTMENTS.get(2), false));
        metersWater.add(new MeterWater("woda ciepla m2", "3455", "metry3", APARTMENTS.get(2), true));
        metersWater.add(new MeterWater("woda zimna m3", "434", "metry3", APARTMENTS.get(3), false));
        metersWater.add(new MeterWater("woda ciepla m3", "3455", "metry3", APARTMENTS.get(3), true));
        return metersWater;
    }

    private static List<ReadingWater> getReadingsWaterOld() {
        List<ReadingWater> readings = new ArrayList<>();
        readings.add(new ReadingWater(FEBRUARY, 80, METERS_WATER.get(0)));
        readings.add(new ReadingWater(FEBRUARY, 10, METERS_WATER.get(1)));
        readings.add(new ReadingWater(FEBRUARY, 10, METERS_WATER.get(2)));
        readings.add(new ReadingWater(FEBRUARY, 15, METERS_WATER.get(3)));
        readings.add(new ReadingWater(FEBRUARY, 15, METERS_WATER.get(4)));
        readings.add(new ReadingWater(FEBRUARY, 12, METERS_WATER.get(5)));
        readings.add(new ReadingWater(FEBRUARY, 12, METERS_WATER.get(6)));
        return readings;
    }

    private static List<ReadingWater> getReadingsWaterNew() {
        List<ReadingWater> readings = new ArrayList<>();
        readings.add(new ReadingWater(MARCH, 100, METERS_WATER.get(0)));
        readings.add(new ReadingWater(MARCH, 11, METERS_WATER.get(1)));
        readings.add(new ReadingWater(MARCH, 11, METERS_WATER.get(2)));
        readings.add(new ReadingWater(MARCH, 20, METERS_WATER.get(3)));
        readings.add(new ReadingWater(MARCH, 20, METERS_WATER.get(4)));
        readings.add(new ReadingWater(MARCH, 15, METERS_WATER.get(5)));
        readings.add(new ReadingWater(MARCH, 15, METERS_WATER.get(6)));
        return readings;
    }

    private static InvoiceEnergy getInvoiceEnergy(List<ReadingEnergy> newReadings) {
        return new InvoiceEnergy("23424", new LocalDate(), 150, newReadings.get(0));
    }

    private static InvoiceWater getInvoiceWater(List<ReadingWater> newReadings) {
        return new InvoiceWater("23424", new LocalDate(), 200, newReadings.get(0));
    }

    private static InvoiceGas getInvoiceGas(List<ReadingGas> newReadings) {
        return new InvoiceGas("23424", new LocalDate(), 300, newReadings.get(0));
    }
}
