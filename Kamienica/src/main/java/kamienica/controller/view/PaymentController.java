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

import kamienica.conventer.ApartmentIB;
import kamienica.conventer.MeterEnergyIB;
import kamienica.conventer.MeterGasIB;
import kamienica.conventer.MeterWaterIB;
import kamienica.conventer.ReadingInvoiceIB;
import kamienica.core.ManagerEnergy;
import kamienica.core.ManagerPayment;
import kamienica.model.Apartment;
import kamienica.model.Division;
import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
import kamienica.model.MeterEnergy;
import kamienica.model.MeterGas;
import kamienica.model.MeterWater;
import kamienica.model.PaymentEnergy;
import kamienica.model.ReadingEnergy;
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

		// ArrayList<Tenant> tenants = (ArrayList<Tenant>)
		// tenantService.getCurrentTenants();
		// ArrayList<Division> division = (ArrayList<Division>)
		// divisionService.getList();
		// ArrayList<Apartment> apartments = (ArrayList<Apartment>)
		// apartmentService.getList();
		//
		// if
		// (!DivisionValidator.validateDivisionForPaymentController(apartments,
		// division, tenants)) {
		// String message = "Lista aktualnych najemców i mieszkań się nie
		// zgadza. Sprawdź algorytm podziału";
		// model.put("error", message);
		// System.out.println("przed invoicami");
		// return new ModelAndView("/Admin/Payment/PaymentRegister", "model",
		// model);
		// }

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
		//
		// PaymentEnergy latestPaymentEnergy =
		// paymentService.getLatestPaymentEnergy();
		// PaymentWater latestPaymentWater =
		// paymentService.getLatestPaymentWater();
		// PaymentGas latestPaymentGas = paymentService.getLatestPaymentGas();
		//
		//
		// System.out.println(latestPaymentEnergy.toString());
		// System.out.println(latestPaymentWater.toString());
		// System.out.println(latestPaymentGas.toString());
		//
		// ArrayList<Date> readingDatesEnergy = (ArrayList<Date>) readingService
		// .getEnergyReadingDatesForPayment(latestPaymentEnergy);
		// ArrayList<Date> readingDatesWater = (ArrayList<Date>) readingService
		// .getWaterReadingDatesForPayment(latestPaymentWater);
		// ArrayList<Date> readingDatesGas = (ArrayList<Date>) readingService
		// .getGasReadingDatesForPayment(latestPaymentGas);
		//
		// List<InvoiceWater> invoiceWater =
		// invoiceService.getInvoicesWaterForPayment(latestPaymentWater);
		// List<InvoiceGas> invoiceGas =
		// invoiceService.getInvoicesGasForPayment(latestPaymentGas);
		// List<InvoiceEnergy> invoiceEnergy =
		// invoiceService.getInvoicesEnergyForPayment(latestPaymentEnergy);
		//
		// System.out.println(invoiceWater.toString());
		// System.out.println(invoiceGas.toString());
		// System.out.println(invoiceEnergy.toString());
		//
		// if (readingDatesEnergy.isEmpty() || invoiceEnergy.isEmpty()) {
		// energy = false;
		// }
		// if (readingDatesWater.isEmpty() || invoiceWater.isEmpty()) {
		// water = false;
		// }
		// if (readingDatesGas.isEmpty() || invoiceGas.isEmpty()) {
		// gas = false;
		// }
		//
		// if (energy == false && water == false && gas == false) {
		// model.put("error", "Brak nowych danych do wprowadzenia");
		// return new ModelAndView("/Admin/Payment/NewPaymentRegister", "model",
		// model);
		// }
		//
		// if (energy) {
		//
		// ManagerPayment.prepareModelForNewEnergyPayment(model,
		// readingDatesEnergy, invoiceEnergy);
		// }
		//
		// if (gas) {
		// ManagerPayment.prepareModelForNewGasPayment(model, readingDatesGas,
		// invoiceGas);
		// }
		// if (water) {
		// ManagerPayment.prepareModelForNewWaterPayment(model,
		// readingDatesWater, invoiceWater);
		//
		// }
		// System.out.println(model.values());

	}

	@RequestMapping("/Admin/Payment/paymentSave")
	public ModelAndView paymentSave(@ModelAttribute("invoiceWrapper") InvoiceWrapper invoiceWrapper,
			BindingResult result) {

		HashMap<String, Object> model = new HashMap<>();
		ArrayList<Tenant> tenants = (ArrayList<Tenant>) tenantService.getCurrentTenants();
		ArrayList<Division> division = (ArrayList<Division>) divisionService.getList();
		ArrayList<Apartment> apartments = (ArrayList<Apartment>) apartmentService.getList();

		if (!DivisionValidator.validateDivisionForPaymentController(apartments, division, tenants)) {
			String message = "Lista aktualnych najemców i mieszkań się nie zgadza. Sprawdź algorytm podziału";
			model.put("error", message);
			return new ModelAndView("/Admin/Payment/PaymentRegister", "model", model);
		}

		if (invoiceWrapper.getEnergy() == null && invoiceWrapper.getWater() == null
				&& invoiceWrapper.getGas() == null) {
			String message = "Nie wybrano żadnych danych...";
			model.put("warning", message);
			return new ModelAndView("/admin/PaymentRegister", "model", model);
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

			System.out.println("listy:");
			System.out.println(readingEnergyOld.toString());
			System.out.println(readingEnergyNew.toString());

			ArrayList<UsageValue> usageEnergy = ManagerEnergy.countEnergyConsupmtion(apartments, readingEnergyOld,
					readingEnergyNew);
			System.out.println("zuzycie:");
			System.out.println(usageEnergy.toString());
			List<PaymentEnergy> paymentEnergy = ManagerPayment.createPaymentEnergyList(tenants,
					invoicesEnergyForCalculation, division, usageEnergy);
			
			paymentService.saveEnergy(paymentEnergy);
		}

		// if (invoiceWrapper.getGasFirst() != null &&
		// invoiceWrapper.getGasLast() != null) {
		//
		// List<Invoice> invoiceGas = (ArrayList<Invoice>)
		// invoiceService.getInvoicesGasForCalulation(
		// invoiceWrapper.getGasFirst().getInvoice(),
		// invoiceWrapper.getGasLast().getInvoice());
		//
		// ArrayList<ReadingGas> gasOld = (ArrayList<ReadingGas>)
		// readingService
		// .getReadingGasByDate(df.format(invoiceWrapper.getGasFirst().getDate()));
		// ArrayList<ReadingGas> gasNew = (ArrayList<ReadingGas>)
		// readingService
		// .getReadingGasByDate(df.format(invoiceWrapper.getGasLast().getDate()));
		//
		// List<ReadingWater> waterNewForGas =
		// readingService.getWaterReadingsForGasConsumption(gasNew.get(0));
		// List<ReadingWater> waterOldForGas =
		// readingService.getWaterReadingsForGasConsumption(waterNewForGas.get(0));
		//
		// ArrayList<UsageValue> usage =
		// ManagerGas.countGasConsumption(apartments, gasOld, gasNew,
		// waterOldForGas,
		// waterNewForGas);
		//
		// ArrayList<PaymentGas> paymentGas =
		// ManagerPayment.createPaymentGasList(tenants, invoiceGas,
		// division,
		// usage,
		// gasNew.get(0).getReadingDate());
		//
		// paymentService.saveGas(paymentGas);
		//
		// }
		// if (invoiceWrapper.getEnergyFirst() != null &&
		// invoiceWrapper.getEnergyLast() != null) {
		//
		// List<Invoice> invoiceEnergy =
		// invoiceService.getInvoicesEnergyForCalulation(
		// invoiceWrapper.getEnergyFirst().getInvoice(),
		// invoiceWrapper.getEnergyLast().getInvoice());
		//
		// ArrayList<ReadingEnergy> energyOld = (ArrayList<ReadingEnergy>)
		// readingService
		// .getReadingEnergyByDate(df.format(invoiceWrapper.getEnergyFirst().getDate()));
		// ArrayList<ReadingEnergy> energyNew = (ArrayList<ReadingEnergy>)
		// readingService
		// .getReadingEnergyByDate(df.format(invoiceWrapper.getEnergyLast().getDate()));
		//
		// ArrayList<UsageValue> usage =
		// ManagerEnergy.countEnergyConsupmtion(apartments, energyOld,
		// energyNew);
		//
		// ArrayList<PaymentEnergy> paymentEnergy =
		// ManagerPayment.createEnergyPaymentList(tenants, invoiceEnergy,
		// division, usage, energyNew.get(0).getReadingDate());
		//
		// paymentService.saveEnergy(paymentEnergy);
		//
		// }
		//
		// if (invoiceWrapper.getWaterFirst() != null &&
		// invoiceWrapper.getWaterLast() != null) {
		//
		// List<Invoice> invoiceWater =
		// invoiceService.getInvoicesWaterForCalulation(
		// invoiceWrapper.getWaterFirst().getInvoice(),
		// invoiceWrapper.getWaterLast().getInvoice());
		//
		// ArrayList<ReadingWater> waterOld = (ArrayList<ReadingWater>)
		// readingService
		// .getReadingWaterByDate(df.format(invoiceWrapper.getWaterFirst().getDate()));
		// ArrayList<ReadingWater> waterNew = (ArrayList<ReadingWater>)
		// readingService
		// .getReadingWaterByDate(df.format(invoiceWrapper.getWaterLast().getDate()));
		//
		// ArrayList<UsageValue> usage =
		// ManagerWater.countWaterConsumption(apartments, waterOld,
		// waterNew);
		//
		// ArrayList<PaymentWater> paymentWater =
		// ManagerPayment.createPaymentWaterList(tenants, invoiceWater,
		// division, usage, waterNew.get(0).getReadingDate());
		//
		// paymentService.saveWater(paymentWater);
		//
		// }

		return new ModelAndView("redirect:/Admin/Payment/paymentList.html");

	}

	// ------------------------------PAYMENTLIST--------------------------------------------------
	@RequestMapping("/Admin/Payment/paymentEnergyList")
	public ModelAndView paymentEnergyList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("media", "Energia");
		model.put("payment", paymentService.getPaymentEnergyList());
		return new ModelAndView("/Admin/Payment/PaymentList2", model);

	}

	@RequestMapping("/Admin/Payment/paymentGasList")
	public ModelAndView paymentGasList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("media", "Gaz");
		model.put("payment", paymentService.getPaymentGasList());
		return new ModelAndView("/Admin/Payment/PaymentList2", model);

	}

	@RequestMapping("/Admin/Payment/paymentWaterList")
	public ModelAndView paymentWaterList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("media", "Woda");
		model.put("payment", paymentService.getPaymentWaterList());
		return new ModelAndView("/Admin/Payment/PaymentList2", model);

	}

}
