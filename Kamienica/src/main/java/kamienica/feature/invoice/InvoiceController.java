package kamienica.feature.invoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kamienica.core.ManagerEnergy;
import kamienica.core.ManagerGas;
import kamienica.core.ManagerPayment;
import kamienica.core.ManagerWater;
import kamienica.core.Media;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentService;
import kamienica.feature.division.Division;
import kamienica.feature.division.DivisionService;
import kamienica.feature.division.DivisionValidator;
import kamienica.feature.meter.MeterService;
import kamienica.feature.payment.PaymentEnergy;
import kamienica.feature.payment.PaymentGas;
import kamienica.feature.payment.PaymentService;
import kamienica.feature.payment.PaymentWater;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingService;
import kamienica.feature.reading.ReadingWater;
import kamienica.feature.tenant.Tenant;
import kamienica.feature.tenant.TenantService;
import kamienica.feature.usagevalue.UsageValue;

@Controller
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private ReadingService readingService;
	@Autowired
	private ApartmentService apartmentService;
	@Autowired
	private TenantService tenantService;
	@Autowired
	private DivisionService divisionService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private MeterService meterService;
	// @InitBinder
	// protected void initBinder(HttpServletRequest request,
	// ServletRequestDataBinder binder) {
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// sdf.setLenient(true);
	// binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	// }
	//
	// Date date = new Date();
	// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	// -------------------REJESTRACJA----------------------------------------------
	@RequestMapping("/Admin/Invoice/invoiceGasRegister")
	public ModelAndView registerInvoiceGas(@ModelAttribute("invoice") InvoiceGas invoice, BindingResult result) {

		HashMap<String, Object> model = new HashMap<>();
		model.put("saveUrl", "/Admin/Invoice/invoiceGasSave");
		model.put("media", "Gas");
		ArrayList<Tenant> tenants = (ArrayList<Tenant>) tenantService.getCurrentTenants();
		ArrayList<Division> division = (ArrayList<Division>) divisionService.getList();
		ArrayList<Apartment> apartments = (ArrayList<Apartment>) apartmentService.getList();

		if (!DivisionValidator.validateDivision(apartments, division, tenants)) {
			String message = "Lista aktualnych najemców i mieszkań się nie zgadza. Sprawdź algorytm podziału";
			model.put("error", message);
			return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
		}

		List<ReadingGas> readings = readingService.getUnresolvedReadingsGas();
		if (readings.isEmpty()) {
			model.put("error", "Brakuje odczytów dla nowej faktury");
		} else {
			model.put("readings", readings);
		}

		return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
	}

	@RequestMapping("/Admin/Invoice/invoiceEnergyRegister")
	public ModelAndView registerInvoiceEnergy(@ModelAttribute("invoice") InvoiceEnergy invoice, BindingResult result) {

		HashMap<String, Object> model = new HashMap<>();
		model.put("saveUrl", "/Admin/Invoice/invoiceEnergySave.html");
		model.put("media", "Energia");
		ArrayList<Tenant> tenants = (ArrayList<Tenant>) tenantService.getCurrentTenants();
		ArrayList<Division> division = (ArrayList<Division>) divisionService.getList();
		ArrayList<Apartment> apartments = (ArrayList<Apartment>) apartmentService.getList();

		if (!DivisionValidator.validateDivision(apartments, division, tenants)) {
			String message = "Lista aktualnych najemców i mieszkań się nie zgadza. Sprawdź algorytm podziału";
			model.put("error", message);
			return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
		}

		List<ReadingEnergy> readings = readingService.getUnresolvedReadingsEnergy();
		if (readings.isEmpty()) {
			model.put("error", "Brakuje odczytów dla nowej faktury");
		} else {
			model.put("readings", readings);
		}

		return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
	}

	@RequestMapping("/Admin/Invoice/invoiceWaterRegister")
	public ModelAndView registerInvoiceWater(@ModelAttribute("invoice") InvoiceWater invoice, BindingResult result) {

		HashMap<String, Object> model = new HashMap<>();
		model.put("saveUrl", "/Admin/Invoice/invoiceWaterSave");
		model.put("media", "Woda");
		ArrayList<Tenant> tenants = (ArrayList<Tenant>) tenantService.getCurrentTenants();
		ArrayList<Division> division = (ArrayList<Division>) divisionService.getList();
		ArrayList<Apartment> apartments = (ArrayList<Apartment>) apartmentService.getList();

		if (!DivisionValidator.validateDivision(apartments, division, tenants)) {
			String message = "Lista aktualnych najemców i mieszkań się nie zgadza. Sprawdź algorytm podziału";
			model.put("error", message);
			return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
		}

		List<ReadingWater> readings = readingService.getUnresolvedReadingsWater();
		if (readings.isEmpty()) {
			model.put("error", "Brakuje odczytów dla nowej faktury");
		} else {
			model.put("readings", readings);
		}

		return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
	}

	// -------------------ZAPIS----------------------------------------------

	@RequestMapping(value = "/Admin/Invoice/invoiceGasSave", method = RequestMethod.POST)
	public ModelAndView invoiceGasSave(@Valid @ModelAttribute("invoice") InvoiceGas invoice, BindingResult result) {

		if (result.hasErrors()) {
			HashMap<String, Object> model = new HashMap<>();
			model.put("saveUrl", "/Admin/Invoice/invoiceGasSave");
			model.put("media", "Gaz");
			return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
		}

		List<ReadingGas> readingGasOld = new ArrayList<>();
		ArrayList<Tenant> tenants = (ArrayList<Tenant>) tenantService.getCurrentTenants();
		ArrayList<Division> division = (ArrayList<Division>) divisionService.getList();
		ArrayList<Apartment> apartments = (ArrayList<Apartment>) apartmentService.getList();

		readingGasOld = readingService.getPreviousReadingGas(invoice.getBaseReading().getReadingDate().toString(), meterService.getIdList(Media.GAS));

		HashMap<String, List<ReadingWater>> waterForGas = readingService.getWaterReadingsForGasConsumption(invoice);
		if (waterForGas.isEmpty()) {
			HashMap<String, Object> model = new HashMap<>();
			String message = "Brakuje odczytów wody. Bez nich niemożliwe jest obliczenie zużycia gazu dla pieca CWU";
			model.put("error", message);
			model.put("saveUrl", "/Admin/Invoice/invoiceGasSave");
			model.put("media", "Gaz");
			return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
		}

		List<ReadingGas> readingGasNew = readingService
				.getReadingGasByDate(invoice.getBaseReading().getReadingDate().toString());

		ArrayList<UsageValue> usageGas = ManagerGas.countConsumption(apartments, readingGasOld, readingGasNew,
				waterForGas.get("old"), waterForGas.get("new"));
		List<PaymentGas> paymentGas = ManagerPayment.createPaymentGasList(tenants, invoice, division, usageGas);

		try {
			invoiceService.saveGas(invoice, paymentGas);
		} catch (ConstraintViolationException e) {
			result.rejectValue("serialNumber", "error.invoice", "Podany numer już istnieje");
			return new ModelAndView("/Admin/Invoice/InvoiceGasRegister");
		}
		return new ModelAndView("redirect:/Admin/Invoice/invoiceGasList.html");
	}

	@RequestMapping(value = "/Admin/Invoice/invoiceWaterSave", method = RequestMethod.POST)
	public ModelAndView invoiceWaterSave(@Valid @ModelAttribute("invoice") InvoiceWater invoice, BindingResult result) {

		if (result.hasErrors()) {
			HashMap<String, Object> model = new HashMap<>();
			model.put("saveUrl", "/Admin/Invoice/invoiceWaterSave");
			model.put("media", "Woda");
			return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
		}

		List<ReadingWater> readingWaterOld = new ArrayList<>();
		ArrayList<Tenant> tenants = (ArrayList<Tenant>) tenantService.getCurrentTenants();
		ArrayList<Division> division = (ArrayList<Division>) divisionService.getList();
		ArrayList<Apartment> apartments = (ArrayList<Apartment>) apartmentService.getList();

		readingWaterOld = readingService.getPreviousReadingWater(invoice.getBaseReading().getReadingDate().toString(),  meterService.getIdList(Media.GAS));

		List<ReadingWater> readingWaterNew = readingService
				.getReadingWaterByDate(invoice.getBaseReading().getReadingDate().toString());

		ArrayList<UsageValue> usageWater = ManagerWater.countConsumption(apartments, readingWaterOld, readingWaterNew);
		List<PaymentWater> paymentWater = ManagerPayment.createPaymentWaterList(tenants, invoice, division, usageWater);

		try {
			invoiceService.saveWater(invoice, paymentWater);
		} catch (ConstraintViolationException e) {
			result.rejectValue("serialNumber", "error.invoice", "Podany numerjuż istnieje");
			return new ModelAndView("/Admin/Invoice/InvoiceWaterRegister");
		}
		return new ModelAndView("redirect:/Admin/Invoice/invoiceWaterList.html");
	}

	@RequestMapping(value = "/Admin/Invoice/invoiceEnergySave", method = RequestMethod.POST)
	public ModelAndView invoiceEnergySave(@Valid @ModelAttribute("invoice") InvoiceEnergy invoice,
			BindingResult result) {

		if (result.hasErrors()) {
			HashMap<String, Object> model = new HashMap<>();
			model.put("saveUrl", "/Admin/Invoice/invoiceEnergySave");
			model.put("media", "Energia");
			return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
		}

		ArrayList<Tenant> tenants = (ArrayList<Tenant>) tenantService.getCurrentTenants();
		ArrayList<Division> division = (ArrayList<Division>) divisionService.getList();
		ArrayList<Apartment> apartments = (ArrayList<Apartment>) apartmentService.getList();

		List<ReadingEnergy> readingEnergyOld = readingService
				.getPreviousReadingEnergy(invoice.getBaseReading().getReadingDate().toString(),  meterService.getIdList(Media.GAS));

		List<ReadingEnergy> readingEnergyNew = readingService
				.getReadingEnergyByDate(invoice.getBaseReading().getReadingDate().toString());

		ArrayList<UsageValue> usageEnergy = ManagerEnergy.countConsupmtion(apartments, readingEnergyOld,
				readingEnergyNew);
		List<PaymentEnergy> paymentEnergy = ManagerPayment.createPaymentEnergyList(tenants, invoice, division,
				usageEnergy);

		try {
			invoiceService.saveEnergy(invoice, paymentEnergy);
		} catch (ConstraintViolationException e) {
			result.rejectValue("serialNumber", "error.invoice", "Podany numerjuż istnieje");
			return new ModelAndView("/Admin/Invoice/InvoiceEnergyRegister");
		}
		return new ModelAndView("redirect:/Admin/Invoice/invoiceEnergyList.html");

	}

	// -------------------LISTA----------------------------------------------

	@RequestMapping("/Admin/Invoice/invoiceGasList")
	public ModelAndView invoiceGasList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("invoice", invoiceService.getGasInvoiceList());
		model.put("editlUrl", "/Admin/Invoice/invoiceGasEdit.html?id=");
		model.put("delUrl", "/Admin/Invoice/invoiceGasDelete.html?id=");
		model.put("media", "Gaz");
		return new ModelAndView("/Admin/Invoice/InvoiceList", model);

	}

	@RequestMapping("/Admin/Invoice/invoiceWaterList")
	public ModelAndView invoiceWaterList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("invoice", invoiceService.getWaterInvoiceList());
		model.put("editlUrl", "/Admin/Invoice/invoiceWaterEdit.html?id=");
		model.put("delUrl", "/Admin/Invoice/invoiceWaterDelete.html?id=");
		model.put("media", "Woda");
		return new ModelAndView("/Admin/Invoice/InvoiceList", model);

	}

	@RequestMapping("/Admin/Invoice/invoiceEnergyList")
	public ModelAndView invoiceEnergyList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("invoice", invoiceService.getEnergyInvoiceList());
		model.put("editlUrl", "/Admin/Invoice/invoiceEnergyEdit.html?id=");
		model.put("delUrl", "/Admin/Invoice/invoiceEnergyDelete.html?id=");
		model.put("media", "Energia");
		return new ModelAndView("/Admin/Invoice/InvoiceList", model);

	}

	// -------------------EDYCJA----------------------------------------------

	@RequestMapping(value = "/Admin/Invoice/invoiceGasEdit")
	public ModelAndView invoiceGasEdit(@RequestParam(value = "id") Long id) {
		HashMap<String, Object> model = new HashMap<>();
		model.put("saveUrl", "/Admin/Invoice/invoiceGasOverwrite.html");
		model.put("media", "Gaz");
		return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model).addObject("invoice",
				invoiceService.getGasByID(id));
	}

	@RequestMapping(value = "/Admin/Invoice/invoiceWaterEdit")
	public ModelAndView edytujFakturaWoda(@RequestParam(value = "id") Long id) {
		HashMap<String, Object> model = new HashMap<>();
		model.put("saveUrl", "/Admin/Invoice/invoiceWaterOverwrite.html");
		model.put("media", "Woda");
		return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model).addObject("invoice",
				invoiceService.getWaterByID(id));
	}

	@RequestMapping(value = "/Admin/Invoice/invoiceEnergyEdit")
	public ModelAndView edytujFakture(@RequestParam(value = "id") Long id) {
		HashMap<String, Object> model = new HashMap<>();
		model.put("saveUrl", "/Admin/Invoice/invoiceEnergyOverwrite.html");
		model.put("media", "Energia");
		return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model).addObject("invoice",
				invoiceService.getEnergyByID(id));
	}

	// --------------------------NADPIS-------------------------------------------------
	@RequestMapping(value = "/Admin/Invoice/invoiceGasOverwrite", method = RequestMethod.POST)
	public ModelAndView invoiceGas(@Valid @ModelAttribute("invoice") InvoiceGas invoice, BindingResult result) {

		if (result.hasErrors()) {
			HashMap<String, Object> model = new HashMap<>();
			model.put("saveUrl", "/Admin/Invoice/invoiceGasOverwrite.html");
			model.put("media", "Gaz");
			return new ModelAndView("/Admin/Invoice/InvoiceRegister");
		}

		InvoiceGas oldInv = invoiceService.getGasByID(invoice.getId());
		double invFactor = (invoice.getTotalAmount() / oldInv.getTotalAmount());
		List<PaymentGas> oldPayments = paymentService.getPaymentGasByInvoice(invoice);
		for (int i = 0; i < oldPayments.size(); i++) {
			oldPayments.get(i).setPaymentAmount(oldPayments.get(i).getPaymentAmount() * invFactor);
		}

		try {
			invoiceService.updateGas(invoice, oldPayments);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			result.rejectValue("serialNumber", "error.invoice", "Podany numerjuż istnieje");
			HashMap<String, Object> model = new HashMap<>();
			model.put("saveUrl", "/Admin/Invoice/invoiceGasOverwrite.html");
			model.put("media", "Gaz");
			return new ModelAndView("/Admin/Invoice/InvoiceRegister");
		}
		return new ModelAndView("redirect:/Admin/Invoice/invoiceGasList.html");
	}

	@RequestMapping(value = "/Admin/Invoice/invoiceWaterOverwrite", method = RequestMethod.POST)
	public ModelAndView invoiceWater(@Valid @ModelAttribute("invoice") InvoiceWater invoice, BindingResult result) {

		if (result.hasErrors()) {
			HashMap<String, Object> model = new HashMap<>();
			model.put("saveUrl", "/Admin/Invoice/invoiceWaterOverwrite.html");
			model.put("media", "Woda");
			return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
		}
		InvoiceWater oldInv = invoiceService.getWaterByID(invoice.getId());
		double invFactor = (invoice.getTotalAmount() / oldInv.getTotalAmount());
		List<PaymentWater> oldPayments = paymentService.getPaymentWaterByInvoice(invoice);
		for (int i = 0; i < oldPayments.size(); i++) {
			oldPayments.get(i).setPaymentAmount(oldPayments.get(i).getPaymentAmount() * invFactor);
		}

		try {

			invoiceService.updateWater(invoice, oldPayments);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			result.rejectValue("serialNumber", "error.invoice", "Podany numerjuż istnieje");
			HashMap<String, Object> model = new HashMap<>();
			model.put("saveUrl", "/Admin/Invoice/invoiceWaterOverwrite.html");
			model.put("media", "Woda");
			return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
		}
		return new ModelAndView("redirect:/Admin/Invoice/invoiceWaterList.html");
	}

	@RequestMapping(value = "/Admin/Invoice/invoiceEnergyOverwrite", method = RequestMethod.POST)
	public ModelAndView invoiceEnergy(@Valid @ModelAttribute("invoice") InvoiceEnergy invoice, BindingResult result) {

		if (result.hasErrors()) {
			HashMap<String, Object> model = new HashMap<>();
			model.put("saveUrl", "/Admin/Invoice/invoiceEnergyOverwrite.html");
			model.put("media", "Energia");
			return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
		}
		InvoiceEnergy oldInv = invoiceService.getEnergyByID(invoice.getId());
		double invFactor = (invoice.getTotalAmount() / oldInv.getTotalAmount());
		List<PaymentEnergy> oldPayments = paymentService.getEnergyByInvoice(invoice);
		for (int i = 0; i < oldPayments.size(); i++) {
			oldPayments.get(i).setPaymentAmount(oldPayments.get(i).getPaymentAmount() * invFactor);
		}

		try {

			invoiceService.updateEnergy(invoice, oldPayments);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			result.rejectValue("serialNumber", "error.invoice", "Podany numerjuż istnieje");
			HashMap<String, Object> model = new HashMap<>();
			model.put("saveUrl", "/Admin/Invoice/invoiceEnergyOverwrite.html");
			model.put("media", "Energia");
			return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
		}
		return new ModelAndView("redirect:/Admin/Invoice/invoiceEnergyList.html");
	}
	// -----------------------------USUN------------------------------------------------

	@RequestMapping(value = "/Admin/Invoice/invoiceGasDelete")
	public ModelAndView invoiceGaz(@RequestParam(value = "id") Long id) {

		invoiceService.deleteGasByID(id);
		return new ModelAndView("redirect:/Admin/Invoice/invoiceGasList.html");

	}

	@RequestMapping(value = "/Admin/Invoice/invoiceWaterDelete")
	public ModelAndView invoiceWoda(@RequestParam(value = "id") Long id) {

		invoiceService.deleteWaterByID(id);
		return new ModelAndView("redirect:/Admin/Invoice/invoiceWaterList.html");

	}

	@RequestMapping(value = "/Admin/Invoice/invoiceEnergyDelete")
	public ModelAndView invoiceEnergia(@RequestParam(value = "id") Long id) {
		invoiceService.deleteEnergyByID(id);
		return new ModelAndView("redirect:/Admin/Invoice/invoiceEnergyList.html");
	}

}
