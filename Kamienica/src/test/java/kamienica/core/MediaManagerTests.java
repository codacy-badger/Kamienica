package kamienica.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import kamienica.apartment.Apartment;
import kamienica.model.Division;
import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
import kamienica.model.MeterEnergy;
import kamienica.model.MeterGas;
import kamienica.model.MeterWater;
import kamienica.model.PaymentEnergy;
import kamienica.model.PaymentGas;
import kamienica.model.PaymentWater;
import kamienica.model.ReadingEnergy;
import kamienica.model.ReadingGas;
import kamienica.model.ReadingWater;
import kamienica.model.Tenant;
import kamienica.model.UsageValue;
import kamienica.testsetup.SetupMethods;
import kamienica.validator.DivisionValidator;

public class MediaManagerTests {

	@Spy
	ArrayList<Apartment> apartments = new ArrayList<Apartment>();
	@Spy
	ArrayList<Tenant> tenants = new ArrayList<Tenant>();
	@Spy
	ArrayList<Division> division = new ArrayList<Division>();
	@Spy
	List<MeterEnergy> metersEnergy = new ArrayList<MeterEnergy>();
	@Spy
	List<MeterGas> metersGas = new ArrayList<MeterGas>();
	@Spy
	List<MeterWater> metersWater = new ArrayList<MeterWater>();
	@Spy
	List<ReadingWater> readingsWaterOld = new ArrayList<ReadingWater>();
	@Spy
	List<ReadingWater> readingsWaterNew = new ArrayList<ReadingWater>();
	@Spy
	List<ReadingGas> readingsGasOld = new ArrayList<ReadingGas>();
	@Spy
	List<ReadingGas> readingsGasNew = new ArrayList<ReadingGas>();
	@Spy
	List<ReadingEnergy> readingsEnergyOld = new ArrayList<ReadingEnergy>();
	@Spy
	List<ReadingEnergy> readingsEnergyNew = new ArrayList<ReadingEnergy>();
	@Spy
	InvoiceWater invoiceWater;
	@Spy
	InvoiceGas invoiceGas;
	@Spy
	InvoiceEnergy invoiceEnergy;

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		apartments = SetupMethods.getApartmentList();
		tenants = SetupMethods.getTenantList(apartments);
		division = SetupMethods.getDivisionList(apartments, tenants);
		metersEnergy = SetupMethods.getMetersEnergy(apartments);
		metersWater = SetupMethods.getMetersWater(apartments);
		metersGas = SetupMethods.getMetersGas(apartments);
		readingsWaterOld = SetupMethods.getReadingsWaterOld(metersWater);
		readingsWaterNew = SetupMethods.getReadingsWaterNew(metersWater);
		readingsEnergyNew = SetupMethods.getReadingsEnergyNew(metersEnergy);
		readingsEnergyOld = SetupMethods.getReadingsEnergyOld(metersEnergy);
		readingsGasNew = SetupMethods.getReadingsGasNew(metersGas);
		readingsGasOld = SetupMethods.getReadingsGasOld(metersGas);
		invoiceEnergy = SetupMethods.getInvoiceEnergy(readingsEnergyNew);
		invoiceGas = SetupMethods.getInvoiceGas(readingsGasNew);
		invoiceWater = SetupMethods.getInvoiceWater(readingsWaterNew);
	}

	@Test
	public void ValidateDivision() {

		Assert.assertEquals(DivisionValidator.validateDivision(apartments, division, tenants), true);
	}

	@Test
	public void consuptionEnergy() {
		ArrayList<Integer> expectedUsageUsage = new ArrayList<>();
		ArrayList<UsageValue> usage = ManagerEnergy.countConsupmtion(apartments, readingsEnergyOld, readingsEnergyNew);
		expectedUsageUsage.addAll(Arrays.asList(10, 5, 15, 5));
		ArrayList<Integer> realUsage = new ArrayList<>();
		for (UsageValue u : usage) {
			realUsage.add((int) u.getUsage());
		}
		Assert.assertEquals(realUsage, expectedUsageUsage);
	}

	@Test
	public void paymentEnergy() {
		ArrayList<UsageValue> usage = SetupMethods.usageEnergy(apartments);
		ArrayList<Double> expectedPayment = new ArrayList<>();
		ArrayList<Double> realPayment = new ArrayList<>();
		ArrayList<PaymentEnergy> payment = ManagerPayment.createPaymentEnergyList(tenants, invoiceEnergy, division,
				usage);
		expectedPayment.addAll(Arrays.asList(64.29, 85.71));

		for (PaymentEnergy pay : payment) {
			realPayment.add(pay.getPaymentAmount());
		}

		Assert.assertEquals(realPayment, expectedPayment);
	}

	@Test
	public void consuptionWater() {
		ArrayList<Integer> expectedUsage = new ArrayList<>();
		ArrayList<UsageValue> usage = ManagerWater.countConsumption(apartments, readingsWaterOld, readingsWaterNew);

		expectedUsage.addAll(Arrays.asList(2, 2, 10, 6));

		ArrayList<Integer> realUsage = new ArrayList<>();
		for (UsageValue u : usage) {
			realUsage.add((int) u.getUsage());
		}
		Assert.assertEquals(realUsage, expectedUsage);
	}

	@Test
	public void paymentWater() {
		ArrayList<UsageValue> usage = SetupMethods.usageWater(apartments);

		ArrayList<Double> expectedPayment = new ArrayList<>();
		ArrayList<Double> realPayment = new ArrayList<>();
		ArrayList<PaymentWater> payment = ManagerPayment.createPaymentWaterList(tenants, invoiceWater, division, usage);
		expectedPayment.addAll(Arrays.asList(90.0, 110.00));

		for (PaymentWater pay : payment) {
			realPayment.add(pay.getPaymentAmount());
		}

		Assert.assertEquals(realPayment, expectedPayment);
	}

	@Test
	public void consuptionGas() {
		ArrayList<Double> expectedUsage = new ArrayList<>();
		ArrayList<UsageValue> usage = ManagerGas.countConsumption(apartments, readingsGasOld, readingsGasNew,
				readingsWaterOld, readingsWaterNew);
		ArrayList<Double> realUsage = new ArrayList<>();
		expectedUsage.addAll(Arrays.asList(8.0, 10.33, 3.67, 5.0));

		for (UsageValue u : usage) {
			realUsage.add(u.getUsage());
		}

		Assert.assertEquals(realUsage, expectedUsage);

	}

	@Test
	public void paymentGas() {
		ArrayList<UsageValue> usage = SetupMethods.usageGas(apartments);

		ArrayList<Double> expectedPayment = new ArrayList<>();
		ArrayList<Double> realPayment = new ArrayList<>();
		ArrayList<PaymentGas> payment = ManagerPayment.createPaymentGasList(tenants, invoiceGas, division, usage);
		expectedPayment.addAll(Arrays.asList(214.78, 85.22));

		for (PaymentGas pay : payment) {
			realPayment.add(pay.getPaymentAmount());
		}

		Assert.assertEquals(realPayment, expectedPayment);
	}
	// --------------------------------------SETUP--------------------------------------------------------------
	// public static ArrayList<Apartment> getApartmentList() {
	// Apartment apartment0 = new Apartment(1, 0, "0000", "Czesc Wspolna");
	// Apartment apartment1 = new Apartment(2, 1, "1111", "Piwnica");
	// Apartment apartment2 = new Apartment(3, 2, "2222", "Parter");
	// Apartment apartment3 = new Apartment(4, 3, "3333", "1 pietro");
	//
	// ArrayList<Apartment> apartments = new ArrayList<Apartment>();
	// apartments.add(apartment0);
	// apartments.add(apartment1);
	// apartments.add(apartment2);
	// apartments.add(apartment3);
	// return apartments;
	// }
	//
	// public static ArrayList<Tenant> getTenantList(List<Apartment> apartments)
	// {
	//
	// Tenant tenant2 = new Tenant("Maciej (Admin)", "Fol", "kowalski@wp.pl",
	// "222222", apartments.get(1));
	// tenant2.setStatus(UserStatus.ACTIVE.getUserStatus());
	// tenant2.setId(1);
	// tenant2.setRole(UserRole.ADMIN.getUserRole());
	// Tenant tenant3 = new Tenant("Adam", "Nowak", "nowak@wp.pl", "111111",
	// apartments.get(2));
	// tenant3.setStatus(UserStatus.ACTIVE.getUserStatus());
	// tenant3.setId(2);
	//
	// ArrayList<Tenant> tenants = new ArrayList<Tenant>();
	// tenants.add(tenant2);
	// tenants.add(tenant3);
	// return tenants;
	// }
	//
	// public static ArrayList<Division> getDivisionList(List<Apartment>
	// apartments, List<Tenant> tenants) {
	// ArrayList<Division> division = new ArrayList<>();
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
	// metersEnergy.add(M0);
	// metersEnergy.add(M1);
	// metersEnergy.add(M2);
	// metersEnergy.add(M3);
	// metersEnergy.add(main);
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
	// metersWater.add(M1cold);
	// metersWater.add(M1hot);
	// metersWater.add(M2cold);
	// metersWater.add(M2hot);
	// metersWater.add(M3cold);
	// metersWater.add(M3hot);
	// metersWater.add(main);
	// return metersWater;
	// }
	//
	// public static List<ReadingWater> getReadingsWaterOld(List<MeterWater>
	// meters) {
	// List<ReadingWater> readings = new ArrayList<>();
	// DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	// try {
	// readings.add(new ReadingWater(format.parse("2015-02-01"), 80,
	// meters.get(6)));
	// readings.add(new ReadingWater(format.parse("2015-02-01"), 10,
	// meters.get(0)));
	// readings.add(new ReadingWater(format.parse("2015-02-01"), 10,
	// meters.get(1)));
	// readings.add(new ReadingWater(format.parse("2015-02-01"), 15,
	// meters.get(2)));
	// readings.add(new ReadingWater(format.parse("2015-02-01"), 15,
	// meters.get(3)));
	// readings.add(new ReadingWater(format.parse("2015-02-01"), 12,
	// meters.get(4)));
	// readings.add(new ReadingWater(format.parse("2015-02-01"), 12,
	// meters.get(5)));
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// return readings;
	// }
	//
	// public static List<ReadingWater> getReadingsWaterNew(List<MeterWater>
	// meters) {
	// List<ReadingWater> readings = new ArrayList<>();
	// DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	// try {
	// readings.add(new ReadingWater(format.parse("2015-03-01"), 100,
	// meters.get(6)));
	// readings.add(new ReadingWater(format.parse("2015-03-01"), 11,
	// meters.get(0)));
	// readings.add(new ReadingWater(format.parse("2015-03-01"), 11,
	// meters.get(1)));
	// readings.add(new ReadingWater(format.parse("2015-03-01"), 20,
	// meters.get(2)));
	// readings.add(new ReadingWater(format.parse("2015-03-01"), 20,
	// meters.get(3)));
	// readings.add(new ReadingWater(format.parse("2015-03-01"), 15,
	// meters.get(4)));
	// readings.add(new ReadingWater(format.parse("2015-03-01"), 15,
	// meters.get(5)));
	// } catch (ParseException e) {
	//
	// e.printStackTrace();
	// }
	// return readings;
	// }
	//
	// public static List<ReadingEnergy> getReadingsEnergyOld(List<MeterEnergy>
	// meters) {
	// List<ReadingEnergy> readingsEnergyOld = new ArrayList<>();
	// DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	// try {
	//
	// readingsEnergyOld.add(new ReadingEnergy(format.parse("2015-02-01"), 5,
	// meters.get(0)));
	// readingsEnergyOld.add(new ReadingEnergy(format.parse("2015-02-01"), 5,
	// meters.get(1)));
	// readingsEnergyOld.add(new ReadingEnergy(format.parse("2015-02-01"), 10,
	// meters.get(2)));
	// readingsEnergyOld.add(new ReadingEnergy(format.parse("2015-02-01"), 15,
	// meters.get(3)));
	// readingsEnergyOld.add(new ReadingEnergy(format.parse("2015-02-01"), 35,
	// meters.get(4)));
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// return readingsEnergyOld;
	// }
	//
	// public static List<ReadingEnergy> getReadingsEnergyNew(List<MeterEnergy>
	// meters) {
	// List<ReadingEnergy> readingsEnergyNew = new ArrayList<>();
	// DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	// try {
	//
	// readingsEnergyNew.add(new ReadingEnergy(format.parse("2015-03-01"), 15,
	// meters.get(0)));
	// readingsEnergyNew.add(new ReadingEnergy(format.parse("2015-03-01"), 10,
	// meters.get(1)));
	// readingsEnergyNew.add(new ReadingEnergy(format.parse("2015-03-01"), 25,
	// meters.get(2)));
	// readingsEnergyNew.add(new ReadingEnergy(format.parse("2015-03-01"), 20,
	// meters.get(3)));
	// readingsEnergyNew.add(new ReadingEnergy(format.parse("2015-03-01"), 70,
	// meters.get(4)));
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// return readingsEnergyNew;
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
	// public static List<ReadingGas> getReadingsGasOld(List<MeterGas> meters) {
	// List<ReadingGas> readingsGasOld = new ArrayList<>();
	// DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	// try {
	// readingsGasOld.add(new ReadingGas(format.parse("2015-03-01"), 73,
	// meters.get(0)));
	// readingsGasOld.add(new ReadingGas(format.parse("2015-03-01"), 10,
	// meters.get(1)));
	// readingsGasOld.add(new ReadingGas(format.parse("2015-03-01"), 12,
	// meters.get(2)));
	// readingsGasOld.add(new ReadingGas(format.parse("2015-03-01"), 10,
	// meters.get(3)));
	// readingsGasOld.add(new ReadingGas(format.parse("2015-03-01"), 18,
	// meters.get(4)));
	// readingsGasOld.add(new ReadingGas(format.parse("2015-03-01"), 11,
	// meters.get(5)));
	// readingsGasOld.add(new ReadingGas(format.parse("2015-03-01"), 12,
	// meters.get(6)));
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// return readingsGasOld;
	// }
	//
	// public static List<ReadingGas> getReadingsGasNew(List<MeterGas> meters) {
	// List<ReadingGas> readingsGasNew = new ArrayList<>();
	// DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	// try {
	//
	// readingsGasNew.add(new ReadingGas(format.parse("2015-03-01"), 100,
	// meters.get(0)));
	// readingsGasNew.add(new ReadingGas(format.parse("2015-03-01"), 15,
	// meters.get(1)));
	// readingsGasNew.add(new ReadingGas(format.parse("2015-03-01"), 15,
	// meters.get(2)));
	// readingsGasNew.add(new ReadingGas(format.parse("2015-03-01"), 20,
	// meters.get(3)));
	// readingsGasNew.add(new ReadingGas(format.parse("2015-03-01"), 20,
	// meters.get(4)));
	// readingsGasNew.add(new ReadingGas(format.parse("2015-03-01"), 15,
	// meters.get(5)));
	// readingsGasNew.add(new ReadingGas(format.parse("2015-03-01"), 15,
	// meters.get(6)));
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// return readingsGasNew;
	// }
	//
	// public static InvoiceEnergy getInvoiceEnergy(List<ReadingEnergy>
	// newReadings) {
	// return new InvoiceEnergy("23424", "energia", new Date(), 150,
	// newReadings.get(0));
	// }
	//
	// public static InvoiceWater getInvoiceWater(List<ReadingWater>
	// newReadings) {
	// return new InvoiceWater("23424", "energia", new Date(), 200,
	// newReadings.get(0));
	// }
	//
	// public static InvoiceGas getInvoiceGas(List<ReadingGas> newReadings) {
	// return new InvoiceGas("23424", "energia", new Date(), 300,
	// newReadings.get(0));
	// }
	//
	//
}
