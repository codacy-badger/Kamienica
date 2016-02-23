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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kamienica.conventer.ApartmentIB;
import kamienica.conventer.MeterEnergyIB;
import kamienica.conventer.MeterGasIB;
import kamienica.conventer.MeterWaterIB;
import kamienica.conventer.ReadingInvoiceIB;
import kamienica.core.ManagerEnergy;
import kamienica.core.ManagerGas;
import kamienica.core.ManagerPayment;
import kamienica.core.ManagerWater;
import kamienica.model.Apartment;
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
import kamienica.service.ApartmentService;
import kamienica.service.DivisionService;
import kamienica.service.InvoiceService;
import kamienica.service.MeterService;
import kamienica.service.PaymentService;
import kamienica.service.ReadingService;
import kamienica.service.TenantService;
import kamienica.validator.DivisionValidator;
import kamienica.wrapper.InvoiceWrapper;
import kamienica.wrapper.ReadingInvoiceForm;

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
	public ModelAndView paymentRegister(@ModelAttribute("invoiceWrapper") InvoiceWrapper invoiceWrapper,
			BindingResult result) {

		HashMap<String, Object> model = new HashMap<>();

		List<InvoiceEnergy> invoiceEnergy = invoiceService.getUnpaidInvoiceEnergy();
		List<InvoiceGas> invoiceGas = invoiceService.getUnpaidInvoiceGas();
		List<InvoiceWater> invoiceWater = invoiceService.getUnpaidInvoiceWater();

		if (invoiceEnergy.isEmpty() && invoiceGas.isEmpty() && invoiceWater.isEmpty()) {
			model.put("error", "Brak danych do wprowadzenia. Wprowadź nowe odczyty i faktury by kontynuować");
			return new ModelAndView("/Admin/Payment/PaymentRegister", "model", model);
		}

		if (!invoiceEnergy.isEmpty()) {
			model.put("energy", invoiceEnergy);
		}
		if (!invoiceGas.isEmpty()) {
			model.put("gas", invoiceGas);
		}
		if (!invoiceWater.isEmpty()) {
			model.put("water", invoiceWater);
		}

		return new ModelAndView("/Admin/Payment/PaymentRegister", "model", model);
	}

	@RequestMapping("/Admin/Payment/paymentSave")
	public ModelAndView paymentSave(@ModelAttribute("invoiceWrapper") InvoiceWrapper invoiceWrapper,
			BindingResult result) {

		HashMap<String, Object> model = new HashMap<>();

		if (invoiceWrapper.getEnergy() == null && invoiceWrapper.getWater() == null
				&& invoiceWrapper.getGas() == null) {
			String message = "Nie wybrano żadnych danych...";
			model.put("warning", message);
			return new ModelAndView("/admin/PaymentRegister", "model", model);
		}

		ArrayList<Tenant> tenants = (ArrayList<Tenant>) tenantService.getCurrentTenants();
		ArrayList<Division> division = (ArrayList<Division>) divisionService.getList();
		ArrayList<Apartment> apartments = (ArrayList<Apartment>) apartmentService.getList();

		if (!DivisionValidator.validateDivisionForPaymentController(apartments, division, tenants)) {
			String message = "Lista aktualnych najemców i mieszkań się nie zgadza. Sprawdź algorytm podziału";
			model.put("error", message);
			return new ModelAndView("/Admin/Payment/PaymentRegister", "model", model);
		}

		if (invoiceWrapper.getEnergy() != null) {
			List<InvoiceEnergy> invoicesEnergyForCalculation = invoiceService
					.getInvoicesEnergyForCalulation(invoiceWrapper.getEnergy());
			List<ReadingEnergy> readingEnergyOld = new ArrayList<>();

			try {
				readingEnergyOld = readingService.getReadingEnergyByDate(
						invoiceService.getLatestPaidEnergy().getBaseReading().getReadingDate().toString());
			} catch (NullPointerException e) {
			}
			List<ReadingEnergy> readingEnergyNew = readingService
					.getReadingEnergyByDate(invoiceWrapper.getEnergy().getBaseReading().getReadingDate().toString());

			ArrayList<UsageValue> usageEnergy = ManagerEnergy.countEnergyConsupmtion(apartments, readingEnergyOld,
					readingEnergyNew);
			List<PaymentEnergy> paymentEnergy = ManagerPayment.createPaymentEnergyList(tenants,
					invoicesEnergyForCalculation, division, usageEnergy);
			paymentService.saveEnergy(paymentEnergy);
		}

		if (invoiceWrapper.getWater() != null ) {

			List<InvoiceWater> invoicesWaterForCalculation = invoiceService
					.getInvoicesWaterForCalulation(invoiceWrapper.getWater());
			List<ReadingWater> readingWaterOld = new ArrayList<>();
			System.out.println(invoicesWaterForCalculation.toString());
			try {
				readingWaterOld = readingService.getReadingWaterByDate(
						invoiceService.getLatestPaidWater().getBaseReading().getReadingDate().toString());
			} catch (NullPointerException e) {
			}
			List<ReadingWater> readingWaterNew = readingService
					.getReadingWaterByDate(invoiceWrapper.getWater().getBaseReading().getReadingDate().toString());

			ArrayList<UsageValue> usageWater = ManagerWater.countWaterConsumption(apartments, readingWaterOld,
					readingWaterNew);
			System.out.println(usageWater.toString());
			List<PaymentWater> paymentWater = ManagerPayment.createPaymentWaterList(tenants,
					invoicesWaterForCalculation, division, usageWater);
			System.out.println(paymentWater.toString());
			paymentService.saveWater(paymentWater);
		}

		if (invoiceWrapper.getGas() != null) {

			List<InvoiceGas> invoicesGasForCalculation = invoiceService
					.getInvoicesGasForCalulation(invoiceWrapper.getGas());
			List<ReadingGas> readingGasOld = new ArrayList<>();

			HashMap<String, List<ReadingWater>> waterForGas = readingService
					.getWaterReadingsForGasConsumption2(invoiceWrapper.getGas());
			if (!waterForGas.isEmpty()) {

				try {
					readingGasOld = readingService.getReadingGasByDate(
							invoiceService.getLatestPaidGas().getBaseReading().getReadingDate().toString());

				} catch (NullPointerException e) {
				}
				List<ReadingGas> readingGasNew = readingService
						.getReadingGasByDate(invoiceWrapper.getGas().getBaseReading().getReadingDate().toString());

				ArrayList<UsageValue> usageGas = ManagerGas.countGasConsumption(apartments, readingGasOld,
						readingGasNew, waterForGas.get("old"), waterForGas.get("new"));
				List<PaymentGas> paymentGas = ManagerPayment.createPaymentGasList(tenants, invoicesGasForCalculation,
						division, usageGas);
				paymentService.saveGas(paymentGas);
			}
		}

		return new ModelAndView("redirect:/Admin/Payment/paymentList.html");

	}

	// ------------------------------PAYMENTLIST--------------------------------------------------
	@RequestMapping("/Admin/Payment/paymentEnergyList")
	public ModelAndView paymentEnergyList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("media", "Energia");
		model.put("url", "Energy");
		model.put("payment", paymentService.getPaymentEnergyList());
		return new ModelAndView("/Admin/Payment/PaymentList2", model);

	}

	@RequestMapping("/Admin/Payment/paymentGasList")
	public ModelAndView paymentGasList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("media", "Gaz");
		model.put("url", "Gas");
		model.put("payment", paymentService.getPaymentGasList());
		return new ModelAndView("/Admin/Payment/PaymentList2", model);

	}

	@RequestMapping("/Admin/Payment/paymentWaterList")
	public ModelAndView paymentWaterList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("media", "Woda");
		model.put("url", "Water");
		model.put("payment", paymentService.getPaymentWaterList());
		return new ModelAndView("/Admin/Payment/PaymentList2", model);

	}

	// ------------------------------PAYMENTdelete--------------------------------------------------
	@RequestMapping(value = "/Admin/Payment/paymentEnergyDelete", params = { "date", "id" })
	public ModelAndView deleteEnergy(@RequestParam(value = "date") String date, @RequestParam(value = "id") int id) {

		paymentService.deleteEnergyByDate(date, id);
		return new ModelAndView("redirect:/Admin/Payment/paymentEnergyList.html");
	}

	@RequestMapping(value = "/Admin/Payment/paymentGasDelete", params = { "date", "id" })
	public ModelAndView deleteGas(@RequestParam(value = "date") String date, @RequestParam(value = "id") int id) {

		paymentService.deleteGasByDate(date, id);
		return new ModelAndView("redirect:/Admin/Payment/paymentGasList.html");
	}

	@RequestMapping(value = "/Admin/Payment/paymentWaterDelete", params = { "date", "id" })
	public ModelAndView deleteWater(@RequestParam(value = "date") String date, @RequestParam(value = "id") int id) {

		paymentService.deleteWaterByDate(date, id);
		return new ModelAndView("redirect:/Admin/Payment/paymentWaterList.html");
	}

}
