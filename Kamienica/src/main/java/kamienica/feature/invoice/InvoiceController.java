package kamienica.feature.invoice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kamienica.core.exception.InvalidDivisionException;
import kamienica.core.enums.Media;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingWater;

@Controller
public class InvoiceController {

	private InvoiceControllerUtils utils = new InvoiceControllerUtils();

	@Autowired
	private InvoiceService invoiceService;

	// -------------------REGISTER----------------------------------------------

	@RequestMapping("/Admin/Invoice/invoiceEnergyRegister")
	public ModelAndView registerInvoiceEnergy(@ModelAttribute("invoice") InvoiceEnergy invoice, BindingResult result) {

		HashMap<String, Object> model = utils.setUrlForEnergy();

		List<ReadingEnergy> readings;
		try {
			readings = invoiceService.prepareForRegistration(Media.ENERGY);
		} catch (InvalidDivisionException e) {
			String message = e.getMessage();
			model.put("error", message);
			return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
		}

		utils.checkIfListIsEmpty(model, readings);

		return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
	}

	@RequestMapping("/Admin/Invoice/invoiceGasRegister")
	public ModelAndView registerInvoiceGas(@ModelAttribute("invoice") InvoiceGas invoice, BindingResult result) {

		HashMap<String, Object> model = new HashMap<>();
		utils.setUrlForGas(model);
		List<ReadingGas> readings;
		try {
			readings = invoiceService.prepareForRegistration(Media.GAS);
		} catch (InvalidDivisionException e) {
			model.put("error", e.getMessage());
			return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
		}
		utils.checkIfListIsEmpty(model, readings);

		return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
	}

	@RequestMapping("/Admin/Invoice/invoiceWaterRegister")
	public ModelAndView registerInvoiceWater(@ModelAttribute("invoice") InvoiceWater invoice, BindingResult result) {

		HashMap<String, Object> model = new HashMap<>();
		model.put("saveUrl", "/Admin/Invoice/invoiceWaterSave");
		model.put("media", "Woda");
		// ArrayList<Tenant> tenants = (ArrayList<Tenant>)
		// tenantService.getActiveTenants();
		// ArrayList<Division> division = (ArrayList<Division>)
		// divisionService.getList();
		// ArrayList<Apartment> apartments = (ArrayList<Apartment>)
		// apartmentService.getList();
		//
		// if (!DivisionValidator.validateDivision(apartments, division,
		// tenants)) {
		// String message = "Lista aktualnych najemców i mieszkań się nie
		// zgadza. Sprawdź algorytm podziału";
		// model.put("error", message);
		// return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model",
		// model);
		// }
		//
		// List<ReadingWater> readings =
		// readingService.getUnresolvedReadingsWater();
		// if (readings.isEmpty()) {
		// model.put("error", "Brakuje odczytów dla nowej faktury");
		// } else {
		// model.put("readings", readings);
		// }
		List<ReadingWater> readings;
		try {
			readings = invoiceService.prepareForRegistration(Media.WATER);
		} catch (InvalidDivisionException e) {
			model.put("error", e.getMessage());
			return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
		}
		utils.checkIfListIsEmpty(model, readings);

		return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
	}

	// -------------------SAVE----------------------------------------------

	@RequestMapping(value = "/Admin/Invoice/invoiceEnergySave", method = RequestMethod.POST)
	public ModelAndView invoiceEnergySave(@Valid @ModelAttribute("invoice") InvoiceEnergy invoice,
			BindingResult result) {

		if (result.hasErrors()) {
			HashMap<String, Object> model = new HashMap<>();
			model.put("saveUrl", "/Admin/Invoice/invoiceEnergySave");
			model.put("media", "Energia");
			return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
		}

		try {
			invoiceService.save(invoice, Media.ENERGY);
		} catch (ConstraintViolationException e) {
			result.rejectValue("serialNumber", "error.invoice", "Podany numerjuż istnieje");
			return new ModelAndView("/Admin/Invoice/InvoiceEnergyRegister");
		}
		return new ModelAndView("redirect:/Admin/Invoice/invoiceEnergyList.html");

	}

	@RequestMapping(value = "/Admin/Invoice/invoiceGasSave", method = RequestMethod.POST)
	public ModelAndView invoiceGasSave(@Valid @ModelAttribute("invoice") InvoiceGas invoice, BindingResult result) {

		if (result.hasErrors()) {
			HashMap<String, Object> model = new HashMap<>();
			utils.setUrlForGas(model);
			return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
		}
		//
		// List<ReadingGas> readingGasOld = new ArrayList<>();
		// ArrayList<Tenant> tenants = (ArrayList<Tenant>)
		// tenantService.getActiveTenants();
		// ArrayList<Division> division = (ArrayList<Division>)
		// divisionService.getList();
		// ArrayList<Apartment> apartments = (ArrayList<Apartment>)
		// apartmentService.getList();

		// readingGasOld =
		// readingService.getPreviousReadingGas(invoice.getBaseReading().getReadingDate(),
		// meterService.getIdList(Media.GAS));

		// HashMap<String, List<ReadingWater>> waterForGas =
		// readingService.getWaterReadingsForGasConsumption(invoice);
		// if (waterForGas.isEmpty()) {
		// HashMap<String, Object> model = new HashMap<>();
		// String message = "Brakuje odczytów wody. Bez nich niemożliwe jest
		// obliczenie zużycia gazu dla pieca CWU";
		// model.put("error", message);
		// model.put("saveUrl", "/Admin/Invoice/invoiceGasSave");
		// model.put("media", "Gaz");
		// return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model",
		// model);
		// }

		// List<ReadingGas> readingGasNew =
		// readingService.getByDate(invoice.getBaseReading().getReadingDate(),
		// Media.GAS);
		//
		// ArrayList<MediaUsage> usageGas =
		// ManagerGas.countConsumption(apartments, readingGasOld, readingGasNew,
		// waterForGas.get("old"), waterForGas.get("new"));
		// List<PaymentGas> paymentGas =
		// ManagerPayment.createPaymentGasList(tenants, invoice, division,
		// usageGas);

		try {
			// invoiceService.saveGas(invoice, paymentGas);
			invoiceService.save(invoice, Media.GAS);
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

		// List<ReadingWater> readingWaterOld = new ArrayList<>();
		// ArrayList<Tenant> tenants = (ArrayList<Tenant>)
		// tenantService.getActiveTenants();
		// ArrayList<Division> division = (ArrayList<Division>)
		// divisionService.getList();
		// ArrayList<Apartment> apartments = (ArrayList<Apartment>)
		// apartmentService.getList();
		//
		// readingWaterOld =
		// readingService.getPreviousReadingWater(invoice.getBaseReading().getReadingDate(),
		// meterService.getIdList(Media.WATER));
		//
		// List<ReadingWater> readingWaterNew =
		// readingService.getByDate(invoice.getBaseReading().getReadingDate(),
		// Media.WATER);
		//
		// ArrayList<MediaUsage> usageWater =
		// ManagerWater.countConsumption(apartments, readingWaterOld,
		// readingWaterNew);
		// List<PaymentWater> paymentWater =
		// ManagerPayment.createPaymentWaterList(tenants, invoice, division,
		// usageWater);
		//
		// try {
		// invoiceService.saveWater(invoice, paymentWater);
		// } catch (ConstraintViolationException e) {
		// result.rejectValue("serialNumber", "error.invoice", "Podany numerjuż
		// istnieje");
		// return new ModelAndView("/Admin/Invoice/InvoiceWaterRegister");
		// }

		try {
			invoiceService.save(invoice, Media.WATER);
		} catch (ConstraintViolationException e) {
			result.rejectValue("serialNumber", "error.invoice", "Podany numerjuż istnieje");
			return new ModelAndView("/Admin/Invoice/InvoiceEnergyRegister");
		}

		return new ModelAndView("redirect:/Admin/Invoice/invoiceWaterList.html");
	}

	// -------------------LIST----------------------------------------------

	@RequestMapping("/Admin/Invoice/invoiceEnergyList")
	public ModelAndView invoiceEnergyList() {
		Map<String, Object> model = new HashMap<>();
		invoiceService.list(model, Media.ENERGY);
		// model.put("invoice", invoiceService.getEnergyInvoiceList());
		// model.put("editlUrl", "/Admin/Invoice/invoiceEnergyEdit.html?id=");
		// model.put("delUrl", "/Admin/Invoice/invoiceEnergyDelete.html?id=");
		// model.put("media", "Energia");
		return new ModelAndView("/Admin/Invoice/InvoiceList", model);

	}

	@RequestMapping("/Admin/Invoice/invoiceGasList")
	public ModelAndView invoiceGasList() {
		Map<String, Object> model = new HashMap<>();
		invoiceService.list(model, Media.GAS);
		// model.put("invoice", invoiceService.getGasInvoiceList());
		// model.put("editlUrl", "/Admin/Invoice/invoiceGasEdit.html?id=");
		// model.put("delUrl", "/Admin/Invoice/invoiceGasDelete.html?id=");
		// model.put("media", "Gaz");
		return new ModelAndView("/Admin/Invoice/InvoiceList", model);

	}

	@RequestMapping("/Admin/Invoice/invoiceWaterList")
	public ModelAndView invoiceWaterList() {
		Map<String, Object> model = new HashMap<>();
		invoiceService.list(model, Media.WATER);
		// model.put("invoice", invoiceService.getWaterInvoiceList());
		// model.put("editlUrl", "/Admin/Invoice/invoiceWaterEdit.html?id=");
		// model.put("delUrl", "/Admin/Invoice/invoiceWaterDelete.html?id=");
		// model.put("media", "Woda");
		return new ModelAndView("/Admin/Invoice/InvoiceList", model);

	}

	// -------------------EDYCJA----------------------------------------------

	// @RequestMapping(value = "/Admin/Invoice/invoiceEnergyEdit")
	// public ModelAndView edytujFakture(@RequestParam(value = "id") Long id) {
	// HashMap<String, Object> model = new HashMap<>();
	// model.put("saveUrl", "/Admin/Invoice/invoiceEnergyOverwrite.html");
	// model.put("media", "Energia");
	// return new ModelAndView("/Admin/Invoice/InvoiceEdit", "model",
	// model).addObject("invoice",
	// invoiceService.getEnergyByID(id));
	// }
	//
	// @RequestMapping(value = "/Admin/Invoice/invoiceGasEdit")
	// public ModelAndView invoiceGasEdit(@RequestParam(value = "id") Long id) {
	// HashMap<String, Object> model = new HashMap<>();
	// model.put("saveUrl", "/Admin/Invoice/invoiceGasOverwrite.html");
	// model.put("media", "Gaz");
	// return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model",
	// model).addObject("invoice",
	// invoiceService.getGasByID(id));
	// }
	//
	// @RequestMapping(value = "/Admin/Invoice/invoiceWaterEdit")
	// public ModelAndView edytujFakturaWoda(@RequestParam(value = "id") Long
	// id) {
	// HashMap<String, Object> model = new HashMap<>();
	// model.put("saveUrl", "/Admin/Invoice/invoiceWaterOverwrite.html");
	// model.put("media", "Woda");
	// return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model",
	// model).addObject("invoice",
	// invoiceService.getWaterByID(id));
	// }
	//
	//
	//
	// //
	// --------------------------OVERWRITE-------------------------------------------------
	//
	// @RequestMapping(value = "/Admin/Invoice/invoiceEnergyOverwrite", method =
	// RequestMethod.POST)
	// public ModelAndView invoiceEnergy(@Valid @ModelAttribute("invoice")
	// InvoiceEnergy invoice, BindingResult result) {
	//
	// if (result.hasErrors()) {
	// HashMap<String, Object> model = new HashMap<>();
	// model.put("saveUrl", "/Admin/Invoice/invoiceEnergyOverwrite.html");
	// model.put("media", "Energia");
	// return new ModelAndView("/Admin/Invoice/InvoiceEdit", "model", model);
	// }
	// // InvoiceEnergy oldInv = invoiceService.getEnergyByID(invoice.getId());
	// // double invFactor = (invoice.getTotalAmount() /
	// // oldInv.getTotalAmount());
	// // List<PaymentEnergy> oldPayments =
	// // paymentService.getEnergyByInvoice(invoice);
	// // for (int i = 0; i < oldPayments.size(); i++) {
	// //
	// oldPayments.get(i).setPaymentAmount(oldPayments.get(i).getPaymentAmount()
	// // * invFactor);
	// // }
	//
	// try {
	// invoiceService.update(invoice, Media.ENERGY);
	// // invoiceService.updateEnergy(invoice, oldPayments);
	// } catch (org.springframework.dao.DataIntegrityViolationException e) {
	// result.rejectValue("serialNumber", "error.invoice", "Podany numerjuż
	// istnieje");
	// HashMap<String, Object> model = new HashMap<>();
	// model.put("saveUrl", "/Admin/Invoice/invoiceEnergyOverwrite.html");
	// model.put("media", "Energia");
	// return new ModelAndView("/Admin/Invoice/InvoiceEdit", "model", model);
	// }
	// return new
	// ModelAndView("redirect:/Admin/Invoice/invoiceEnergyList.html");
	// }
	//
	// @RequestMapping(value = "/Admin/Invoice/invoiceGasOverwrite", method =
	// RequestMethod.POST)
	// public ModelAndView invoiceGas(@Valid @ModelAttribute("invoice")
	// InvoiceGas invoice, BindingResult result) {
	//
	// if (result.hasErrors()) {
	// HashMap<String, Object> model = new HashMap<>();
	// model.put("saveUrl", "/Admin/Invoice/invoiceGasOverwrite.html");
	// model.put("media", "Gaz");
	// return new ModelAndView("/Admin/Invoice/InvoiceRegister");
	// }
	//
	// InvoiceGas oldInv = invoiceService.getGasByID(invoice.getId());
	// double invFactor = (invoice.getTotalAmount() / oldInv.getTotalAmount());
	// List<PaymentGas> oldPayments =
	// paymentService.getPaymentGasByInvoice(invoice);
	// for (int i = 0; i < oldPayments.size(); i++) {
	// oldPayments.get(i).setPaymentAmount(oldPayments.get(i).getPaymentAmount()
	// * invFactor);
	// }
	//
	// try {
	// invoiceService.updateGas(invoice, oldPayments);
	// } catch (org.springframework.dao.DataIntegrityViolationException e) {
	// result.rejectValue("serialNumber", "error.invoice", "Podany numerjuż
	// istnieje");
	// HashMap<String, Object> model = new HashMap<>();
	// model.put("saveUrl", "/Admin/Invoice/invoiceGasOverwrite.html");
	// model.put("media", "Gaz");
	// return new ModelAndView("/Admin/Invoice/InvoiceRegister");
	// }
	// return new ModelAndView("redirect:/Admin/Invoice/invoiceGasList.html");
	// }
	//
	// @RequestMapping(value = "/Admin/Invoice/invoiceWaterOverwrite", method =
	// RequestMethod.POST)
	// public ModelAndView invoiceWater(@Valid @ModelAttribute("invoice")
	// InvoiceWater invoice, BindingResult result) {
	//
	// if (result.hasErrors()) {
	// HashMap<String, Object> model = new HashMap<>();
	// model.put("saveUrl", "/Admin/Invoice/invoiceWaterOverwrite.html");
	// model.put("media", "Woda");
	// return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model",
	// model);
	// }
	// InvoiceWater oldInv = invoiceService.getWaterByID(invoice.getId());
	// double invFactor = (invoice.getTotalAmount() / oldInv.getTotalAmount());
	// List<PaymentWater> oldPayments =
	// paymentService.getPaymentWaterByInvoice(invoice);
	// for (int i = 0; i < oldPayments.size(); i++) {
	// oldPayments.get(i).setPaymentAmount(oldPayments.get(i).getPaymentAmount()
	// * invFactor);
	// }
	//
	// try {
	//
	// invoiceService.updateWater(invoice, oldPayments);
	// } catch (org.springframework.dao.DataIntegrityViolationException e) {
	// result.rejectValue("serialNumber", "error.invoice", "Podany numerjuż
	// istnieje");
	// HashMap<String, Object> model = new HashMap<>();
	// model.put("saveUrl", "/Admin/Invoice/invoiceWaterOverwrite.html");
	// model.put("media", "Woda");
	// return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model",
	// model);
	// }
	// return new ModelAndView("redirect:/Admin/Invoice/invoiceWaterList.html");
	// }

	// -----------------------------DELETE------------------------------------------------

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
