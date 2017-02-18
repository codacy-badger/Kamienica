package kamienica.testutils;

import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import kamienica.model.enums.Status;
import kamienica.model.enums.UserRole;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class EntityProvider {

    private static final LocalDate FEBRUARY = LocalDate.parse("2015-02-01");
    private static final LocalDate MARCH = LocalDate.parse("2015-03-01");

    public static final Residence RESIDENCE = createResidence();
    public static final List<Apartment> APARTMENTS = getApartmentList();
    public static final List<Tenant> TENANTS = getTenantList();

    public static final List<Meter> METERS_ENERGY = getMetersEnergy();
    public static final List<Meter> METERS_WATER = getMeterWater();
    public static final List<Meter> METERS_GAS = getMeterGas();

    public static final List<ReadingDetails> ENERGY_READING_DETAILS = createReadingDetailsEnergy();
    public static final List<ReadingDetails> GAS_READING_DETAILS = createReadingDetailsGas();


    public static final List<ReadingDetails> WATER_READING_DETAILS = createReadingDetailsWater();


    public static final List<Reading> ENERGY_OLD = getReadingsEnergyOld();
    public static final List<Reading> ENERGY_NEW = getReadingsEnergyNew();
    public static final List<Reading> ENERGY_NEW_MINUS = getReadingsEnergyNewForMinusResult();

    public static final List<Reading> GAS_OLD = getReadingsGasOld();
    public static final List<Reading> GAS_NEW = getReadingsGasNew();
    public static final List<Reading> WATER_OLD = getReadingsWaterOld();
    public static final List<Reading> WATER_NEW = getReadingsWaterNew();
    public static final List<Division> DIVISION = getDivisionList();
    public static final List<Division> DIVISION_WRONG = getWrongDivisionList();


    // --------------------------------------SETUP--------------------------------------------------------------
    private static List<Apartment> getApartmentList() {
        Apartment apartment0 = new Apartment(0, "0000", "Czesc Wspolna", RESIDENCE);
        Apartment apartment1 = new Apartment(1, "1111", "Piwnica", RESIDENCE);
        Apartment apartment2 = new Apartment(2, "2222", "Parter", RESIDENCE);
        Apartment apartment3 = new Apartment(3, "3333", "1 pietro", RESIDENCE);

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
        tenant2.setRole(UserRole.OWNER);
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

    private static List<Meter> getMeterGas() {
        List<Meter> metersGas = new ArrayList<>();
        metersGas.add(new Meter("Glowny Licznik gazu", "rt", "kWh", null, RESIDENCE, true, Status.ACTIVE, false, false, Media.GAS));
        metersGas.add(new Meter("Licznik gaz czesc wspolna- podloga", "4566", "kWh", APARTMENTS.get(0), RESIDENCE, false, Status.ACTIVE, false, false, Media.GAS));
        metersGas.add(new Meter("Licznik gazu kaloryfera czesci wspolnej", "fdsf", "kWh", APARTMENTS.get(0), RESIDENCE, false, Status.ACTIVE, false, false, Media.GAS));
        metersGas.add(new Meter("Licznik gaz M1", "4566", "kWh", APARTMENTS.get(1), RESIDENCE, false, Status.ACTIVE, false, false, Media.GAS));
        metersGas.add(new Meter("Licznik gaz M2", "4566", "kWh", APARTMENTS.get(2), RESIDENCE, false, Status.ACTIVE, false, false, Media.GAS));
        metersGas.add(new Meter("Licznik gaz M3", "4566", "kWh", APARTMENTS.get(3), RESIDENCE, false, Status.ACTIVE, false, false, Media.GAS));
        metersGas.add(new Meter("Licznik CWU", "4555", "kWh", null, RESIDENCE, false, Status.ACTIVE, true, false, Media.GAS));
        return metersGas;
    }

    private static List<Meter> getMetersEnergy() {
        List<Meter> metersEnergy = new ArrayList<>();
        metersEnergy.add(new Meter("Glowny Licznik energii", "354", "kWh", null, RESIDENCE, true, Status.ACTIVE, false, false, Media.ENERGY));
        metersEnergy.add(new Meter("Licznik czesci administracyjnej", "89/0", "kWh", APARTMENTS.get(0), RESIDENCE, false, Status.ACTIVE, false, false, Media.ENERGY));
        metersEnergy.add(new Meter("Licznik Energii M1", "89/0", "kWh", APARTMENTS.get(1), RESIDENCE, false, Status.ACTIVE, false, false, Media.ENERGY));
        metersEnergy.add(new Meter("Licznik Energii M2", "89/0", "kWh", APARTMENTS.get(2), RESIDENCE, false, Status.ACTIVE, false, false, Media.ENERGY));
        metersEnergy.add(new Meter("Licznik Energii M3", "89/0", "kWh", APARTMENTS.get(3), RESIDENCE, false, Status.ACTIVE, false, false, Media.ENERGY));
        return metersEnergy;
    }

    private static List<Meter> getMeterWater() {
        List<Meter> metersWater = new ArrayList<>();
        metersWater.add(new Meter("Licznik woda glowny", "55", "m3", null, RESIDENCE, false, Status.ACTIVE, false, false, Media.WATER));
        metersWater.add(new Meter("woda zimna m1", "434", "metry3", APARTMENTS.get(1), RESIDENCE, false, Status.ACTIVE, false, false, Media.WATER));
        metersWater.add(new Meter("woda ciepla m1", "3455", "metry3", APARTMENTS.get(1), RESIDENCE, false, Status.ACTIVE, false, true, Media.WATER));
        metersWater.add(new Meter("woda zimna m2", "434", "metry3", APARTMENTS.get(2), RESIDENCE, false, Status.ACTIVE, false, false, Media.WATER));
        metersWater.add(new Meter("woda ciepla m2", "3455", "metry3", APARTMENTS.get(2), RESIDENCE, false, Status.ACTIVE, false, true, Media.WATER));
        metersWater.add(new Meter("woda zimna m3", "434", "metry3", APARTMENTS.get(3), RESIDENCE, false, Status.ACTIVE, false, false, Media.WATER));
        metersWater.add(new Meter("woda ciepla m3", "3455", "metry3", APARTMENTS.get(3), RESIDENCE, false, Status.ACTIVE, false, true, Media.WATER));
        return metersWater;
    }

    private static List<ReadingDetails> createReadingDetailsWater() {
        List<ReadingDetails> details = new ArrayList<>();
        return details;
    }

    private static List<ReadingDetails> createReadingDetailsGas() {
        List<ReadingDetails> details = new ArrayList<>();
        return details;
    }

    private static List<ReadingDetails> createReadingDetailsEnergy() {
        List<ReadingDetails> details = new ArrayList<>();
        return details;
    }


    private static List<Reading> getReadingsGasOld() {
        List<Reading> readingsGasOld = new ArrayList<>();
        readingsGasOld.add(new Reading(FEBRUARY, 73, METERS_GAS.get(0)));
        readingsGasOld.add(new Reading(FEBRUARY, 10, METERS_GAS.get(1)));
        readingsGasOld.add(new Reading(FEBRUARY, 12, METERS_GAS.get(2)));
        readingsGasOld.add(new Reading(FEBRUARY, 10, METERS_GAS.get(3)));
        readingsGasOld.add(new Reading(FEBRUARY, 18, METERS_GAS.get(4)));
        readingsGasOld.add(new Reading(FEBRUARY, 11, METERS_GAS.get(5)));
        readingsGasOld.add(new Reading(FEBRUARY, 12, METERS_GAS.get(6)));
        return readingsGasOld;
    }

    private static List<Reading> getReadingsGasNew() {
        List<Reading> readingsGasNew = new ArrayList<>();
        readingsGasNew.add(new Reading(MARCH, 100, METERS_GAS.get(0)));
        readingsGasNew.add(new Reading(MARCH, 15, METERS_GAS.get(1)));
        readingsGasNew.add(new Reading(MARCH, 15, METERS_GAS.get(2)));
        readingsGasNew.add(new Reading(MARCH, 20, METERS_GAS.get(3)));
        readingsGasNew.add(new Reading(MARCH, 20, METERS_GAS.get(4)));
        readingsGasNew.add(new Reading(MARCH, 15, METERS_GAS.get(5)));
        readingsGasNew.add(new Reading(MARCH, 15, METERS_GAS.get(6)));
        return readingsGasNew;
    }


    private static List<Reading> getReadingsEnergyOld() {
        List<Reading> readingsEnergyOld = new ArrayList<>();
        readingsEnergyOld.add(new Reading(FEBRUARY, 5, METERS_ENERGY.get(0)));
        readingsEnergyOld.add(new Reading(FEBRUARY, 5, METERS_ENERGY.get(1)));
        readingsEnergyOld.add(new Reading(FEBRUARY, 10, METERS_ENERGY.get(2)));
        readingsEnergyOld.add(new Reading(FEBRUARY, 15, METERS_ENERGY.get(3)));
        readingsEnergyOld.add(new Reading(FEBRUARY, 35, METERS_ENERGY.get(4)));
        return readingsEnergyOld;
    }

    private static List<Reading> getReadingsEnergyNew() {
        List<Reading> readingsEnergyNew = new ArrayList<>();
        readingsEnergyNew.add(new Reading(MARCH, 15, METERS_ENERGY.get(0)));
        readingsEnergyNew.add(new Reading(MARCH, 10, METERS_ENERGY.get(1)));
        readingsEnergyNew.add(new Reading(MARCH, 25, METERS_ENERGY.get(2)));
        readingsEnergyNew.add(new Reading(MARCH, 20, METERS_ENERGY.get(3)));
        readingsEnergyNew.add(new Reading(MARCH, 70, METERS_ENERGY.get(4)));
        return readingsEnergyNew;
    }

    private static List<Reading> getReadingsEnergyNewForMinusResult() {
        List<Reading> readingsEnergyNew = new ArrayList<>();
        readingsEnergyNew.add(new Reading(MARCH, 1, METERS_ENERGY.get(0)));
        readingsEnergyNew.add(new Reading(MARCH, 1, METERS_ENERGY.get(1)));
        readingsEnergyNew.add(new Reading(MARCH, 2, METERS_ENERGY.get(2)));
        readingsEnergyNew.add(new Reading(MARCH, 2, METERS_ENERGY.get(3)));
        readingsEnergyNew.add(new Reading(MARCH, 7, METERS_ENERGY.get(4)));
        return readingsEnergyNew;
    }


    //--------------------------------------WATER--------------------------


    private static List<Reading> getReadingsWaterOld() {
        List<Reading> readings = new ArrayList<>();
        readings.add(new Reading(FEBRUARY, 80, METERS_WATER.get(0)));
        readings.add(new Reading(FEBRUARY, 10, METERS_WATER.get(1)));
        readings.add(new Reading(FEBRUARY, 10, METERS_WATER.get(2)));
        readings.add(new Reading(FEBRUARY, 15, METERS_WATER.get(3)));
        readings.add(new Reading(FEBRUARY, 15, METERS_WATER.get(4)));
        readings.add(new Reading(FEBRUARY, 12, METERS_WATER.get(5)));
        readings.add(new Reading(FEBRUARY, 12, METERS_WATER.get(6)));
        return readings;
    }

    private static List<Reading> getReadingsWaterNew() {
        List<Reading> readings = new ArrayList<>();
        readings.add(new Reading(MARCH, 100, METERS_WATER.get(0)));
        readings.add(new Reading(MARCH, 11, METERS_WATER.get(1)));
        readings.add(new Reading(MARCH, 11, METERS_WATER.get(2)));
        readings.add(new Reading(MARCH, 20, METERS_WATER.get(3)));
        readings.add(new Reading(MARCH, 20, METERS_WATER.get(4)));
        readings.add(new Reading(MARCH, 15, METERS_WATER.get(5)));
        readings.add(new Reading(MARCH, 15, METERS_WATER.get(6)));
        return readings;
    }


    private static Invoice getInvoiceEnergy(List<Reading> newReadings) {
        return new Invoice("23424", new LocalDate(), 150, RESIDENCE, newReadings.get(0).getReadingDetails());
    }

    private static Invoice getInvoiceWater(List<Reading> newReadings) {
        return new Invoice("23424", new LocalDate(), 200, RESIDENCE, newReadings.get(0).getReadingDetails());
    }

    private static Invoice getInvoiceGas(List<Reading> newReadings) {
        return new Invoice("23424", new LocalDate(), 300, RESIDENCE, newReadings.get(0).getReadingDetails());
    }

    private static Residence createResidence() {
        return new Residence(1L, "Swietojanska", "23", "gdynia");
    }
}
