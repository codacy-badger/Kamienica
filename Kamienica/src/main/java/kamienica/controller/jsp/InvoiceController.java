package kamienica.controller.jsp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kamienica.core.enums.Media;
import kamienica.core.exception.InvalidDivisionException;
import kamienica.feature.invoice.InvoiceControllerUtils;
import kamienica.feature.invoice.InvoiceService;
import kamienica.feature.reading.ReadingEnergy;
import kamienica.feature.reading.ReadingGas;
import kamienica.feature.reading.ReadingWater;
import kamienica.model.InvoiceEnergy;
import kamienica.model.InvoiceGas;
import kamienica.model.InvoiceWater;

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
			readings = invoiceService.getUnpaidReadingForNewIncvoice(Media.ENERGY);
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
			readings = invoiceService.getUnpaidReadingForNewIncvoice(Media.GAS);
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
		List<ReadingWater> readings;
		try {
			readings = invoiceService.getUnpaidReadingForNewIncvoice(Media.WATER);
		} catch (InvalidDivisionException e) {
			model.put("error", e.getMessage());
			return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
		}
		utils.checkIfListIsEmpty(model, readings);

		return new ModelAndView("/Admin/Invoice/InvoiceRegister", "model", model);
	}


	// -------------------LIST----------------------------------------------

	@RequestMapping("/Admin/Invoice/invoiceEnergyList")
	public ModelAndView invoiceEnergyList() {
		Map<String, Object> model = new HashMap<>();
		invoiceService.list(model, Media.ENERGY);
		return new ModelAndView("/Admin/Invoice/InvoiceList", model);

	}

	@RequestMapping("/Admin/Invoice/invoiceGasList")
	public ModelAndView invoiceGasList() {
		Map<String, Object> model = new HashMap<>();
		invoiceService.list(model, Media.GAS);
		return new ModelAndView("/Admin/Invoice/InvoiceList", model);

	}

	@RequestMapping("/Admin/Invoice/invoiceWaterList")
	public ModelAndView invoiceWaterList() {
		Map<String, Object> model = new HashMap<>();
		invoiceService.list(model, Media.WATER);
		return new ModelAndView("/Admin/Invoice/InvoiceList", model);

	}


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

	/// rest pages----------------------------------------------------

	@RequestMapping(value = "/Admin/Invoice/energy")
	public ModelAndView energyRest() {
		return new ModelAndView("/Admin/Invoice/energy");
	}

	@RequestMapping(value = "/Admin/Invoice/gas")
	public ModelAndView gasRest() {
		return new ModelAndView("/Admin/Invoice/gas");
	}

	@RequestMapping(value = "/Admin/Invoice/water")
	public ModelAndView waterRest() {
		return new ModelAndView("/Admin/Invoice/water");
	}
}
