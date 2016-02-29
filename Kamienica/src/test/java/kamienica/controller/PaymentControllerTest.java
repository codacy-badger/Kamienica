package kamienica.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import kamienica.controller.view.PaymentController;
import kamienica.model.Apartment;
import kamienica.model.Division;
import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
import kamienica.model.MeterEnergy;
import kamienica.model.MeterGas;
import kamienica.model.MeterWater;
import kamienica.model.ReadingEnergy;
import kamienica.model.ReadingGas;
import kamienica.model.ReadingWater;
import kamienica.model.Tenant;
import kamienica.service.ApartmentService;
import kamienica.service.DivisionService;
import kamienica.service.InvoiceService;
import kamienica.service.MeterService;
import kamienica.service.PaymentService;
import kamienica.service.ReadingService;
import kamienica.service.TenantService;
import kamienica.testsetup.SetupMethods;
import kamienica.validator.DivisionValidator;

public class PaymentControllerTest {

	@Mock
	private ApartmentService apartmentService;
	@Mock
	private InvoiceService invoiceService;
	@Mock
	private MeterService meterService;
	@Mock
	private TenantService tenantService;
	@Mock
	private DivisionService divisionService;
	@Mock
	private ReadingService readingService;
	@Mock
	private PaymentService paymentService;

	@Mock
	MessageSource message;

	@InjectMocks
	PaymentController controller;

	@Spy
	List<Apartment> apartments = new ArrayList<Apartment>();
	@Spy
	List<Tenant> tenants = new ArrayList<Tenant>();
	@Spy
	List<Division> division = new ArrayList<Division>();
	@Spy
	List<MeterEnergy> metersEnergy = new ArrayList<MeterEnergy>();
	@Spy
	List<MeterGas> metersGas = new ArrayList<MeterGas>();
	@Spy
	List<MeterWater> metersWater = new ArrayList<MeterWater>();
	@Spy
	List<ReadingWater> readingsWater = new ArrayList<ReadingWater>();
	@Spy
	List<ReadingGas> readingsGas = new ArrayList<ReadingGas>();
	@Spy
	List<ReadingEnergy> readingsEnergy = new ArrayList<ReadingEnergy>();
	@Spy
	List<InvoiceWater> invoiceWater = new ArrayList<>();
	@Spy
	List<InvoiceGas> invoiceGas = new ArrayList<>();
	@Spy
	List<InvoiceEnergy> invoiceEnergy = new ArrayList<>();
	@Spy
	HashMap<String, Object> myModel = new HashMap<>();

	@Mock
	BindingResult result;

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		apartments = SetupMethods.getApartmentList();
		tenants = SetupMethods.getTenantList(apartments);
		division = SetupMethods.getDivisionList(apartments, tenants);
		metersEnergy = SetupMethods.getMetersEnergy(apartments);
		metersWater = SetupMethods.getMetersWater(apartments);
		metersGas = SetupMethods.getMetersGas(apartments);
		readingsWater = SetupMethods.getReadingsWater(metersWater);
		readingsEnergy = SetupMethods.getReadingsEnergy(metersEnergy);
		readingsGas = SetupMethods.getReadingsGas(metersGas);

	}

	// ArrayList<Tenant> tenants = (ArrayList<Tenant>)
	// tenantService.getCurrentTenants();
	// ArrayList<Division> division = (ArrayList<Division>)
	// divisionService.getList();
	// ArrayList<Apartment> apartments = (ArrayList<Apartment>)
	// apartmentService.getList();
	@Test
	public void ValidateDivision() {
		when(tenantService.getCurrentTenants()).thenReturn(tenants);
		when(divisionService.getList()).thenReturn(division);
		when(apartmentService.getList()).thenReturn(apartments);
		Assert.assertEquals(DivisionValidator.validateDivision(apartments, division, tenants),
				true);	
	}
	
	@Test
	public void prepareList() {
		when(tenantService.getCurrentTenants()).thenReturn(tenants);
		when(divisionService.getList()).thenReturn(division);
		when(apartmentService.getList()).thenReturn(apartments);
	
	}
	// @Test
	// public void paymentRegister() {
	// when(apartmentService.getList()).thenReturn(apartments);
	// when(tenantService.getList()).thenReturn(tenants);
	// when(divisionService.getList()).thenReturn(division);
	// when()
	// Assert.assertEquals(controller.apartmentList().getModel(), myModel);
	// Assert.assertEquals(myModel.get("apartment"), apartments);
	// verify(service, atLeastOnce()).getList();
	// }

	// ---------------------SETUP------------------------------------------------
//	private List<Apartment> getApartmentList() {
//		Apartment apartment0 = new Apartment(1, 0, "0000", "Czesc Wspolna");
//		Apartment apartment1 = new Apartment(2, 1, "1111", "Piwnica");
//		Apartment apartment2 = new Apartment(3, 2, "2222", "Parter");
//		Apartment apartment3 = new Apartment(4, 3, "3333", "1 pietro");
//
//		ArrayList<Apartment> apartments = new ArrayList<Apartment>();
//		apartments.add(apartment0);
//		apartments.add(apartment1);
//		apartments.add(apartment2);
//		apartments.add(apartment3);
//		return apartments;
//	}
//
//	private List<Tenant> getTenantList(List<Apartment> apartments) {
//		Tenant tenant2 = new Tenant("Maciej (Admin)", "Fol", "kowalski@wp.pl", "222222", apartments.get(1));
//		Tenant tenant3 = new Tenant("Adam", "Nowak", "nowak@wp.pl", "111111", apartments.get(2));
//
//		ArrayList<Tenant> tenants = new ArrayList<Tenant>();
//		tenants.add(tenant2);
//		tenants.add(tenant3);
//		return tenants;
//	}
//
//	private List<Division> getDivisionList(List<Apartment> apartments, List<Tenant> tenants) {
//		division.add(new Division(1, new Date(), tenants.get(0), apartments.get(0), 0.5));
//		division.add(new Division(2, new Date(), tenants.get(0), apartments.get(1), 1));
//		division.add(new Division(3, new Date(), tenants.get(0), apartments.get(2), 0));
//		division.add(new Division(4, new Date(), tenants.get(0), apartments.get(3), 1));
//
//		division.add(new Division(6, new Date(), tenants.get(1), apartments.get(0), 0.5));
//		division.add(new Division(7, new Date(), tenants.get(1), apartments.get(1), 0));
//		division.add(new Division(8, new Date(), tenants.get(1), apartments.get(2), 1));
//		division.add(new Division(9, new Date(), tenants.get(1), apartments.get(3), 0));
//
//		return division;
//	}
//
//	DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//
//	// ------------------------------LICZNIKI--------------------------------------------------------------------
//	private List<MeterEnergy> getMetersEnergy(List<Apartment> apartments) {
//		MeterEnergy main = new MeterEnergy("Glowny Licznik energii", "354", "kWh", null);
//		MeterEnergy M0 = new MeterEnergy("Licznik czesci administracyjnej", "89/0", "kWh", apartments.get(0));
//		MeterEnergy M1 = new MeterEnergy("Licznik Energii M1", "89/0", "kWh", apartments.get(1));
//		MeterEnergy M2 = new MeterEnergy("Licznik Energii M2", "89/0", "kWh", apartments.get(2));
//		MeterEnergy M3 = new MeterEnergy("Licznik Energii M3", "89/0", "kWh", apartments.get(3));
//
//		metersEnergy.add(main);
//		metersEnergy.add(M1);
//		metersEnergy.add(M2);
//		metersEnergy.add(M3);
//		metersEnergy.add(M0);
//		return metersEnergy;
//	}
//
//	private List<MeterWater> getMetersWater(List<Apartment> apartments) {
//		MeterWater main = new MeterWater("Licznik woda glowny", "55", "m3", null, false);
//		MeterWater M1cold = new MeterWater("woda zimna m1", "434", "metry3", apartments.get(1), false);
//		MeterWater M1hot = new MeterWater("woda ciepla m1", "3455", "metry3", apartments.get(1), true);
//		MeterWater M2cold = new MeterWater("woda zimna m2", "434", "metry3", apartments.get(2), false);
//		MeterWater M2hot = new MeterWater("woda ciepla m2", "3455", "metry3", apartments.get(2), true);
//		MeterWater M3cold = new MeterWater("woda zimna m3", "434", "metry3", apartments.get(3), false);
//		MeterWater M3hot = new MeterWater("woda ciepla m3", "3455", "metry3", apartments.get(3), true);
//
//		metersWater.add(main);
//		metersWater.add(M1cold);
//		metersWater.add(M1hot);
//		metersWater.add(M2cold);
//		metersWater.add(M2hot);
//		metersWater.add(M3cold);
//		metersWater.add(M3hot);
//		return metersWater;
//	}
//
//	public List<MeterGas> getMetersGas(List<Apartment> apartments) {
//		metersGas.add(new MeterGas("Glowny Licznik gazu", "rt", "kWh", null, false));
//		metersGas.add(new MeterGas("Licznik gaz czesc wspolna- podloga", "4566", "kWh", apartments.get(0), false));
//		metersGas.add(new MeterGas("Licznik gazu kaloryfera czesci wspolnej", "fdsf", "kWh", apartments.get(0), false));
//		metersGas.add(new MeterGas("Licznik gaz M1", "4566", "kWh", apartments.get(1), false));
//		metersGas.add(new MeterGas("Licznik gaz M2", "4566", "kWh", apartments.get(2), false));
//		metersGas.add(new MeterGas("Licznik gaz M3", "4566", "kWh", apartments.get(3), false));
//		metersGas.add(new MeterGas("Licznik CWU", "4555", "kWh", null, true));
//		return metersGas;
//	}

}
