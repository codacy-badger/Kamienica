package kamienica.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import kamienica.core.ManagerPayment;
import kamienica.model.Apartment;
import kamienica.model.Division;
import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
import kamienica.model.PaymentEnergy;
import kamienica.model.PaymentGas;
import kamienica.model.PaymentWater;
import kamienica.model.Tenant;
import kamienica.service.ApartmentService;
import kamienica.service.DivisionService;
import kamienica.service.InvoiceService;
import kamienica.service.MeterService;
import kamienica.service.PaymentService;
import kamienica.service.ReadingService;
import kamienica.service.TenantService;
import kamienica.validator.DivisionValidator;
import kamienica.wrapper.PaymentForm;

public class PaymentLogic {
	
	@Autowired
	private ApartmentService apartmentService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private MeterService meterService;
	@Autowired
	private TenantService tenantService;
	@Autowired
	private DivisionService divisionService;
	@Autowired
	private ReadingService readingService;
	@Autowired
	private PaymentService paymentService;
	
	public ModelAndView register( PaymentForm paymentForm) {
		boolean gas = true;
		boolean water = true;
		boolean energy = true;

		HashMap<String, Object> model = new HashMap<>();
		ArrayList<Tenant> tenants = (ArrayList<Tenant>) tenantService.getCurrentTenants();
		ArrayList<Division> division = (ArrayList<Division>) divisionService.getList();
		ArrayList<Apartment> apartments = (ArrayList<Apartment>) apartmentService.getList();

		if (!DivisionValidator.validateDivisionForPaymentController(apartments, division, tenants)) {
			String message = "Lista aktualnych najemców i mieszkań się nie zgadza. Sprawdź algorytm podziału";
			model.put("message", message);
			return new ModelAndView("/Admin/Payment/PaymentRegister", "model", model);
		}

		PaymentEnergy latestPaymentEnergy = paymentService.getLatestPaymentEnergy();
		PaymentWater latestPaymentWater = paymentService.getLatestPaymentWater();
		PaymentGas latestPaymentGas = paymentService.getLatestPaymentGas();

		ArrayList<Date> readingDatesEnergy = (ArrayList<Date>) readingService
				.getEnergyReadingDatesForPayment(latestPaymentEnergy);
		ArrayList<Date> readingDatesWater = (ArrayList<Date>) readingService
				.getWaterReadingDatesForPayment(latestPaymentWater);
		ArrayList<Date> readingDatesGas = (ArrayList<Date>) readingService
				.getGasReadingDatesForPayment(latestPaymentGas);

		List<InvoiceWater> invoiceWater = invoiceService
				.getInvoicesWaterForPayment(latestPaymentWater);
		List<InvoiceGas> invoiceGas =  invoiceService.getInvoicesGasForPayment(latestPaymentGas);
		List<InvoiceEnergy> invoiceEnergy =  invoiceService
				.getInvoicesEnergyForPayment(latestPaymentEnergy);

		
		if (readingDatesEnergy.isEmpty() || invoiceEnergy.isEmpty()) {
			energy = false;
		}
		if (readingDatesWater.isEmpty() || invoiceWater.isEmpty()) {
			water = false;
		}
		if (readingDatesGas.isEmpty() || invoiceGas.isEmpty()) {
			gas = false;
		}

		if (energy == false && water == false && gas == false) {
			model.put("error", "Brak nowych danych do wprowadzenia");
			return new ModelAndView("/Admin/Payment/NewPaymentRegister", "model", model);
		}

		if (energy) {
			
			ManagerPayment.prepareModelForNewEnergyPayment(model, readingDatesEnergy, invoiceEnergy);
		}

		if (gas) {
			ManagerPayment.prepareModelForNewGasPayment(model, readingDatesGas, invoiceGas);
		}
		if (water) {
			ManagerPayment.prepareModelForNewWaterPayment(model, readingDatesWater, invoiceWater);

		}

		return new ModelAndView("/Admin/Payment/NewPaymentRegister", "model", model);
	}
}
