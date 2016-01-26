package kamienica.controller.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kamienica.core.ManagerEnergy;
import kamienica.core.ManagerGas;
import kamienica.core.ManagerPayment;
import kamienica.core.ManagerWater;
import kamienica.forms.PaymentForm;
import kamienica.forms.ReadingInvoiceForm;
import kamienica.initBinder.ApartmentIB;
import kamienica.initBinder.MeterEnergyIB;
import kamienica.initBinder.MeterGasIB;
import kamienica.initBinder.MeterWaterIB;
import kamienica.initBinder.ReadingInvoiceIB;
import kamienica.model.Apartment;
import kamienica.model.Division;
import kamienica.model.Invoice;
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
import kamienica.service.ApartmentService;
import kamienica.service.DivisionService;
import kamienica.service.InvoiceService;
import kamienica.service.MeterService;
import kamienica.service.PaymentService;
import kamienica.service.ReadingService;
import kamienica.service.TenantService;
import kamienica.validator.DivisionValidator;

@Controller
public class PaymentController {

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

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Apartment.class, new ApartmentIB(this.apartmentService));
		binder.registerCustomEditor(MeterEnergy.class, new MeterEnergyIB(this.meterService));
		binder.registerCustomEditor(MeterGas.class, new MeterGasIB(this.meterService));
		binder.registerCustomEditor(MeterWater.class, new MeterWaterIB(this.meterService));
		binder.registerCustomEditor(ReadingInvoiceForm.class, new ReadingInvoiceIB(this.invoiceService));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping("/Admin/Payment/paymentList")
	public ModelAndView paymentList() {
		return new ModelAndView("/Admin/Payment/PaymentList");
	}

	@RequestMapping("/Admin/Payment/paymentRegister")
	public ModelAndView paymentRegister(@ModelAttribute("paymentForm") PaymentForm paymentForm, BindingResult result) {
		
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

		return new ModelAndView("/Admin/Payment/PaymentRegister", "model", model);
	}

	@RequestMapping("/Admin/Payment/paymentSave")
	public ModelAndView paymentSave(@ModelAttribute("paymentForm") PaymentForm paymentForm, BindingResult result) {

		HashMap<String, Object> model = new HashMap<>();
		ArrayList<Tenant> tenants = (ArrayList<Tenant>) tenantService.getCurrentTenants();
		ArrayList<Division> division = (ArrayList<Division>) divisionService.getList();
		ArrayList<Apartment> apartments = (ArrayList<Apartment>) apartmentService.getList();

		if (!DivisionValidator.validateDivisionForPaymentController(apartments, division, tenants)) {
			String message = "Lista aktualnych najemców i mieszkań się nie zgadza. Sprawdź algorytm podziału";
			model.put("message", message);
			return new ModelAndView("/admin/PaymentRegister", "model", model);
		}

		if (paymentForm.getGasFirst() != null && paymentForm.getGasLast() != null) {

			List<Invoice> invoiceGas = (ArrayList<Invoice>) invoiceService.getInvoicesGasForCalulation(
					paymentForm.getGasFirst().getInvoice(), paymentForm.getGasLast().getInvoice());

			ArrayList<ReadingGas> gasOld = (ArrayList<ReadingGas>) readingService
					.getReadingGasByDate(df.format(paymentForm.getGasFirst().getDate()));
			ArrayList<ReadingGas> gasNew = (ArrayList<ReadingGas>) readingService
					.getReadingGasByDate(df.format(paymentForm.getGasLast().getDate()));

			List<ReadingWater> waterNewForGas = readingService.getWaterReadingsForGasConsumption(gasNew.get(0));
			List<ReadingWater> waterOldForGas = readingService.getWaterReadingsForGasConsumption(waterNewForGas.get(0));

			ArrayList<UsageValue> usage = ManagerGas.countGasConsumption(apartments, gasOld, gasNew, waterOldForGas,
					waterNewForGas);

			ArrayList<PaymentGas> paymentGas = ManagerPayment.createPaymentGasList(tenants, invoiceGas, division, usage,
					gasNew.get(0).getReadingDate());

			paymentService.saveGas(paymentGas);

		}
		if (paymentForm.getEnergyFirst() != null && paymentForm.getEnergyLast() != null) {

			List<Invoice> invoiceEnergy =  invoiceService.getInvoicesEnergyForCalulation(
					paymentForm.getEnergyFirst().getInvoice(), paymentForm.getEnergyLast().getInvoice());

			ArrayList<ReadingEnergy> energyOld = (ArrayList<ReadingEnergy>) readingService
					.getReadingEnergyByDate(df.format(paymentForm.getEnergyFirst().getDate()));
			ArrayList<ReadingEnergy> energyNew = (ArrayList<ReadingEnergy>) readingService
					.getReadingEnergyByDate(df.format(paymentForm.getEnergyLast().getDate()));

			ArrayList<UsageValue> usage = ManagerEnergy.countEnergyConsupmtion(apartments, energyOld, energyNew);

			ArrayList<PaymentEnergy> paymentEnergy = ManagerPayment.createEnergyPaymentList(tenants, invoiceEnergy,
					division, usage, energyNew.get(0).getReadingDate());

			paymentService.saveEnergy(paymentEnergy);

		}

		if (paymentForm.getWaterFirst() != null && paymentForm.getWaterLast() != null) {

			List<Invoice> invoiceWater =  invoiceService.getInvoicesWaterForCalulation(
					paymentForm.getWaterFirst().getInvoice(), paymentForm.getWaterLast().getInvoice());

			ArrayList<ReadingWater> waterOld = (ArrayList<ReadingWater>) readingService
					.getReadingWaterByDate(df.format(paymentForm.getWaterFirst().getDate()));
			ArrayList<ReadingWater> waterNew = (ArrayList<ReadingWater>) readingService
					.getReadingWaterByDate(df.format(paymentForm.getWaterLast().getDate()));

			ArrayList<UsageValue> usage = ManagerWater.countWaterConsumption(apartments, waterOld, waterNew);

			ArrayList<PaymentWater> paymentWater = ManagerPayment.createPaymentWaterList(tenants, invoiceWater,
					division, usage, waterNew.get(0).getReadingDate());

			paymentService.saveWater(paymentWater);

		}

		return new ModelAndView("redirect:/Admin/Payment/paymentList.html");

	}

	// ------------------------------PAYMENTLIST--------------------------------------------------
	@RequestMapping("/Admin/Payment/paymentEnergyList")
	public ModelAndView paymentEnergyList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("payment", paymentService.getPaymentEnergyList());
		return new ModelAndView("/Admin/Payment/PaymentList2", model);

	}

	@RequestMapping("/Admin/Payment/paymentGasList")
	public ModelAndView paymentGasList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("payment", paymentService.getPaymentGasList());
		return new ModelAndView("/Admin/Payment/PaymentList2", model);

	}

	@RequestMapping("/Admin/Payment/paymentWaterList")
	public ModelAndView paymentWaterList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("payment", paymentService.getPaymentWaterList());
		return new ModelAndView("/Admin/Payment/PaymentList2", model);

	}

}
