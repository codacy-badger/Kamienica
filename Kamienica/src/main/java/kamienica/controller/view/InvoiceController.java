package kamienica.controller.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
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

import kamienica.core.ManagerEnergy;
import kamienica.core.ManagerGas;
import kamienica.core.ManagerPayment;
import kamienica.core.ManagerWater;
import kamienica.model.Apartment;
import kamienica.model.Division;
import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
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
import kamienica.service.PaymentService;
import kamienica.service.ReadingService;
import kamienica.service.TenantService;
import kamienica.validator.DivisionValidator;

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

	public void setFakturaService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;

	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	Date date = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping("/Admin/Invoice/registerInvoice")
	public ModelAndView invoiceRegistration() {

		return new ModelAndView("/Admin/Invoice/RegisterInvoice");
	}

	@RequestMapping("/Admin/Invoice/invoiceList")
	public ModelAndView invoiceLsit() {
		return new ModelAndView("/Admin/Invoice/InvoiceList");
	}

	// -------------------REJESTRACJA----------------------------------------------
	@RequestMapping("/Admin/Invoice/invoiceGasRegister")
	public ModelAndView registerInvoiceGas(@ModelAttribute("invoice") InvoiceGas invoice, BindingResult result) {

		HashMap<String, Object> model = new HashMap<>();
		ArrayList<Tenant> tenants = (ArrayList<Tenant>) tenantService.getCurrentTenants();
		ArrayList<Division> division = (ArrayList<Division>) divisionService.getList();
		ArrayList<Apartment> apartments = (ArrayList<Apartment>) apartmentService.getList();

		if (!DivisionValidator.validateDivision(apartments, division, tenants)) {
			String message = "Lista aktualnych najemców i mieszkań się nie zgadza. Sprawdź algorytm podziału";
			model.put("error", message);
			return new ModelAndView("/Admin/Invoice/InvoiceGasRegister", "model", model);
		}

		List<ReadingGas> readings = readingService.getUnresolvedReadingsGas();
		if (readings.isEmpty()) {
			model.put("error", "Brakuje odczytów dla nowej faktury");
		} else {
			model.put("readings", readings);
		}

		return new ModelAndView("/Admin/Invoice/InvoiceGasRegister", "model", model);
	}

	@RequestMapping("/Admin/Invoice/invoiceEnergyRegister")
	public ModelAndView registerInvoiceEnergy(@ModelAttribute("invoice") InvoiceEnergy invoice, BindingResult result) {

		HashMap<String, Object> model = new HashMap<>();

		ArrayList<Tenant> tenants = (ArrayList<Tenant>) tenantService.getCurrentTenants();
		ArrayList<Division> division = (ArrayList<Division>) divisionService.getList();
		ArrayList<Apartment> apartments = (ArrayList<Apartment>) apartmentService.getList();

		if (!DivisionValidator.validateDivision(apartments, division, tenants)) {
			String message = "Lista aktualnych najemców i mieszkań się nie zgadza. Sprawdź algorytm podziału";
			model.put("error", message);
			return new ModelAndView("/Admin/Invoice/InvoiceEnergyRegister", "model", model);
		}

		List<ReadingEnergy> readings = readingService.getUnresolvedReadingsEnergy();
		if (readings.isEmpty()) {
			model.put("error", "Brakuje odczytów dla nowej faktury");
		} else {
			model.put("readings", readings);
		}

		return new ModelAndView("/Admin/Invoice/InvoiceEnergyRegister", "model", model);
	}

	@RequestMapping("/Admin/Invoice/invoiceWaterRegister")
	public ModelAndView registerInvoiceWater(@ModelAttribute("invoice") InvoiceWater invoice, BindingResult result) {

		HashMap<String, Object> model = new HashMap<>();
		ArrayList<Tenant> tenants = (ArrayList<Tenant>) tenantService.getCurrentTenants();
		ArrayList<Division> division = (ArrayList<Division>) divisionService.getList();
		ArrayList<Apartment> apartments = (ArrayList<Apartment>) apartmentService.getList();

		if (!DivisionValidator.validateDivision(apartments, division, tenants)) {
			String message = "Lista aktualnych najemców i mieszkań się nie zgadza. Sprawdź algorytm podziału";
			model.put("error", message);
			return new ModelAndView("/Admin/Invoice/InvoiceWaterRegister", "model", model);
		}

		List<ReadingWater> readings = readingService.getUnresolvedReadingsWater();
		if (readings.isEmpty()) {
			model.put("error", "Brakuje odczytów dla nowej faktury");
		} else {
			model.put("readings", readings);
		}

		return new ModelAndView("/Admin/Invoice/InvoiceWaterRegister", "model", model);
	}

	// -------------------ZAPIS----------------------------------------------

	@RequestMapping("/Admin/Invoice/invoiceGasSave")
	public ModelAndView invoiceGasSave(@Valid @ModelAttribute("invoice") InvoiceGas invoice, BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("/Admin/Invoice/InvoiceGasRegister");
		}

		List<ReadingGas> readingGasOld = new ArrayList<>();
		ArrayList<Tenant> tenants = (ArrayList<Tenant>) tenantService.getCurrentTenants();
		ArrayList<Division> division = (ArrayList<Division>) divisionService.getList();
		ArrayList<Apartment> apartments = (ArrayList<Apartment>) apartmentService.getList();

		readingGasOld = readingService.getPreviousReadingGas(invoice.getBaseReading().getReadingDate().toString());

		HashMap<String, List<ReadingWater>> waterForGas = readingService.getWaterReadingsForGasConsumption(invoice);
		if (waterForGas.isEmpty()) {
			HashMap<String, Object> model = new HashMap<>();
			String message = "Brakuje odczytów wody. Bez nich niemożliwe jest obliczenie zużycia gazu dla pieca CWU";
			model.put("error", message);
			return new ModelAndView("/Admin/Invoice/InvoiceWaterRegister", "model", model);
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

	@RequestMapping("/Admin/Invoice/invoiceWaterSave")
	public ModelAndView invoiceWaterSave(@Valid @ModelAttribute("invoice") InvoiceWater invoice, BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("/Admin/Invoice/InvoiceWaterRegister");
		}

		List<ReadingWater> readingWaterOld = new ArrayList<>();
		ArrayList<Tenant> tenants = (ArrayList<Tenant>) tenantService.getCurrentTenants();
		ArrayList<Division> division = (ArrayList<Division>) divisionService.getList();
		ArrayList<Apartment> apartments = (ArrayList<Apartment>) apartmentService.getList();

		readingWaterOld = readingService.getPreviousReadingWater(invoice.getBaseReading().getReadingDate().toString());

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

	@RequestMapping("/Admin/Invoice/invoiceEnergySave")
	public ModelAndView invoiceEnergySave(@Valid @ModelAttribute("invoice") InvoiceEnergy invoice,
			BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("/Admin/Invoice/InvoiceEnergyRegister");
		}

		ArrayList<Tenant> tenants = (ArrayList<Tenant>) tenantService.getCurrentTenants();
		ArrayList<Division> division = (ArrayList<Division>) divisionService.getList();
		ArrayList<Apartment> apartments = (ArrayList<Apartment>) apartmentService.getList();

		List<ReadingEnergy> readingEnergyOld = readingService
				.getPreviousReadingEnergy(invoice.getBaseReading().getReadingDate().toString());

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
		return new ModelAndView("/Admin/Invoice/InvoiceGasList", model);

	}

	@RequestMapping("/Admin/Invoice/invoiceWaterList")
	public ModelAndView invoiceWaterList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("invoice", invoiceService.getWaterInvoiceList());
		return new ModelAndView("/Admin/Invoice/InvoiceWaterList", model);

	}

	@RequestMapping("/Admin/Invoice/invoiceEnergyList")
	public ModelAndView invoiceEnergyList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("invoice", invoiceService.getEnergyInvoiceList());
		return new ModelAndView("/Admin/Invoice/InvoiceEnergyList", model);

	}

	// -------------------EDYCJA----------------------------------------------

	@RequestMapping(value = "/Admin/Invoice/invoiceGasEdit", params = { "id" })
	public ModelAndView invoiceGasEdit(@RequestParam(value = "id") int id) {
		InvoiceGas invoice = (InvoiceGas) invoiceService.getGasByID(id);
		ModelAndView mvc = new ModelAndView("/Admin/Invoice/InvoiceGasEdit");
		mvc.addObject("invoice", invoice);
		return mvc;
	}

	@RequestMapping(value = "/Admin/Invoice/invoiceWaterEdit", params = { "id" })
	public ModelAndView edytujFakturaWoda(@RequestParam(value = "id") int id) {
		InvoiceWater invoice = (InvoiceWater) invoiceService.getWaterByID(id);
		ModelAndView mvc = new ModelAndView("/Admin/Invoice/InvoiceWaterEdit");
		mvc.addObject("invoice", invoice);
		return mvc;
	}

	@RequestMapping(value = "/Admin/Invoice/invoiceEnergyEdit", params = { "id" })
	public ModelAndView edytujFakture(@RequestParam(value = "id") int id) {
		InvoiceEnergy invoice = (InvoiceEnergy) invoiceService.getEnergyByID(id);
		ModelAndView mvc = new ModelAndView("/Admin/Invoice/InvoiceEnergyEdit");
		mvc.addObject("invoice", invoice);
		return mvc;
	}

	// --------------------------NADPIS-------------------------------------------------
	@RequestMapping("/Admin/Invoice/invoiceGasOverwrite")
	public ModelAndView invoiceGas(@Valid @ModelAttribute("invoice") InvoiceGas invoice, BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("/Admin/Invoice/InvoiceGasEdit");
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
			return new ModelAndView("/Admin/Invoice/InvoiceGasEdit");
		}
		return new ModelAndView("redirect:/Admin/Invoice/invoiceGasList.html");
	}

	@RequestMapping("/Admin/Invoice/invoiceWaterOverwrite")
	public ModelAndView invoiceWater(@Valid @ModelAttribute("invoice") InvoiceWater invoice, BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("/Admin/Invoice/InvoiceWaterEdit");
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
			return new ModelAndView("/Admin/Invoice/InvoiceWaterEdit");
		}
		return new ModelAndView("redirect:/Admin/Invoice/invoiceWaterList.html");
	}

	@RequestMapping("/Admin/Invoice/invoiceEnergyOverwrite")
	public ModelAndView invoiceEnergy(@Valid @ModelAttribute("invoice") InvoiceEnergy invoice, BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("/Admin/Invoice/InvoiceEnergyEdit");
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
			return new ModelAndView("/Admin/Invoice/InvoiceEnergyEdit");
		}
		return new ModelAndView("redirect:/Admin/Invoice/invoiceEnergyList.html");
	}
	// -----------------------------USUN------------------------------------------------

	@RequestMapping(value = "/Admin/Invoice/invoiceGasDelete", params = { "id" })
	public ModelAndView invoiceGaz(@RequestParam(value = "id") int id) {

		invoiceService.deleteGasByID(id);
		return new ModelAndView("/Admin/Invoice/invoiceGasList.html");

	}

	@RequestMapping(value = "/Admin/Invoice/invoiceWaterDelete", params = { "id" })
	public ModelAndView invoiceWoda(@RequestParam(value = "id") int id) {

		invoiceService.deleteWaterByID(id);
		return new ModelAndView("/Admin/Invoice/invoiceWaterList.html");

	}

	@RequestMapping(value = "/Admin/Invoice/invoiceEnergyDelete", params = { "id" })
	public ModelAndView invoiceEnergia(@RequestParam(value = "id") int id) {
		invoiceService.deleteEnergyByID(id);
		return new ModelAndView("redirect:/Admin/Invoice/invoiceEnergyList.html");
	}

}
