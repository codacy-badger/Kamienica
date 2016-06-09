package kamienica.controller.jsp;

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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kamienica.feature.apartment.Apartment;
import kamienica.model.MeterEnergy;
import kamienica.model.MeterGas;
import kamienica.model.MeterWater;
import kamienica.service.ApartmentService;
import kamienica.service.MeterService;

@Controller
public class MeterController {

	@Autowired
	private ApartmentService apartmentService;
	@Autowired
	private MeterService meterService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	// ------------------Register-------------------------------------

	@RequestMapping("/Admin/Meter/meterEnergyRegister")
	public ModelAndView meterEnergyRegister(@ModelAttribute("meter") MeterEnergy meter, BindingResult result) {
		Map<String, Object> model = prepareModel();
		return new ModelAndView("/Admin/Meter/MeterEnergyRegister", "model", model);
	}

	@RequestMapping("/Admin/Meter/meterWaterRegister")
	public ModelAndView meterWaterRegister(@ModelAttribute("meter") MeterWater meter, BindingResult result) {

		Map<String, Object> model = prepareModel();
		return new ModelAndView("/Admin/Meter/MeterWaterRegister", "model", model);
	}

	@RequestMapping("/Admin/Meter/meterGasRegister")
	public ModelAndView meterGasRegister(@ModelAttribute("meter") MeterGas meter, BindingResult result) {
		Map<String, Object> model = prepareModel();
		return new ModelAndView("/Admin/Meter/MeterGasRegister", "model", model);
	}

	// ----------------------------SAVE------------------------------------------

	@RequestMapping("/Admin/Meter/meterEnergySave")
	public ModelAndView meterEnergySave(@Valid @ModelAttribute("meter") MeterEnergy meter, BindingResult result) {

		if (result.hasErrors()) {
			Map<String, Object> model = prepareModel();
			return new ModelAndView("/Admin/Meter/MeterEnergyRegister", "model", model);
		}
		try {
			meterService.saveEnergy(meter);
		} catch (ConstraintViolationException e) {
			Map<String, Object> model = prepareModel();
			result.rejectValue("serialNumber", "error.meter", "Istnieje już w bazie licznik z takim numerem seryjnym");
			return new ModelAndView("/Admin/Meter/MeterEnergyRegister", "model", model);

		}
		return new ModelAndView("redirect:/Admin/Meter/meterEnergyList.html");
	}

	@RequestMapping("/Admin/Meter/meterWaterSave")
	public ModelAndView meterWaterSave(@Valid @ModelAttribute("meter") MeterWater meter, BindingResult result) {
		if (meter.getApartment() == null && meter.getIsWarmWater() == true) {
			result.rejectValue("isWarmWater", "error.meter",
					"Licznik cz�ci wsp�lnej nie może być licznikiem ciep�ej wody");
		}

		if (result.hasErrors()) {
			Map<String, Object> model = prepareModel();
			return new ModelAndView("/Admin/Meter/MeterWaterRegister", "model", model);
		}
		try {
			meterService.saveWater(meter);
		} catch (ConstraintViolationException e) {
			Map<String, Object> model = prepareModel();
			result.rejectValue("serialNumber", "error.meter", "Istnieje już w bazie licznik z takim numerem seryjnym");
			return new ModelAndView("/Admin/Meter/MeterWaterRegister", "model", model);

		}
		return new ModelAndView("redirect:/Admin/Meter/meterWaterList.html");
	}

	@RequestMapping("/Admin/Meter/meterGasSave")
	public ModelAndView meterGasSave(@Valid @ModelAttribute("meter") MeterGas meter, BindingResult result) {

		if (meter.getApartment() == null && meter.isCwu() == true) {
			result.rejectValue("cwu", "error.meter", "Licznik częsci wspólnej nie może być licznikiem CWU");
		}
		if (result.hasErrors()) {
			Map<String, Object> model = prepareModel();
			return new ModelAndView("/Admin/Meter/MeterGasRegister", "model", model);
		}
		try {
			meterService.saveGas(meter);
		} catch (ConstraintViolationException e) {
			Map<String, Object> model = prepareModel();
			result.rejectValue("serialNumber", "error.meter", "Istnieje już w bazie licznik z takim numerem seryjnym");
			return new ModelAndView("/Admin/Meter/MeterGasRegister", "model", model);

		}

		return new ModelAndView("redirect:/Admin/Meter/meterGasList.html");
	}

	// -----------------------------LIST-----------------------------------

	@RequestMapping("/Admin/Meter/meterEnergyList")
	public ModelAndView meterEnergyList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("meter", meterService.getEnergyList());

		// model.put("meter", meterService.getEnergyList());
		return new ModelAndView("/Admin/Meter/MeterEnergyList", model);

	}

	@RequestMapping("/Admin/Meter/meterWaterList")
	public ModelAndView meterWaterList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("meter", meterService.getWaterList());
		return new ModelAndView("/Admin/Meter/MeterWaterList", model);

	}

	@RequestMapping("/Admin/Meter/meterGasList")
	public ModelAndView meterGasList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("meter", meterService.getGasList());
		return new ModelAndView("/Admin/Meter/MeterGasList", model);

	}

	// ----------------------EDIT----------------------------------------

	@RequestMapping(value = "/Admin/Meter/meterEnergyEdit", params = { "id" })
	public ModelAndView meterEnergyEdit(@RequestParam(value = "id") int id) {
		Map<String, Object> model = prepareModel();
		MeterEnergy meter = meterService.getEnergyByID(id);
		ModelAndView mvc = new ModelAndView("/Admin/Meter/MeterEnergyEdit", "model", model);
		mvc.addObject("meter", meter);
		return mvc;
	}

	@RequestMapping(value = "/Admin/Meter/meterWaterEdit", params = { "id" })
	public ModelAndView meterWaterEdit(@RequestParam(value = "id") int id) {
		Map<String, Object> model = prepareModel();
		MeterWater meter = meterService.getWaterByID(id);
		ModelAndView mvc = new ModelAndView("/Admin/Meter/MeterWaterEdit", "model", model);
		mvc.addObject("meter", meter);
		return mvc;
	}

	@RequestMapping(value = "/Admin/Meter/meterGasEdit", params = { "id" })
	public ModelAndView meterGasEdit(@RequestParam(value = "id") int id) {
		Map<String, Object> model = prepareModel();
		MeterGas meter = meterService.getGasByID(id);
		ModelAndView mvc = new ModelAndView("/Admin/Meter/MeterGasEdit", "model", model);
		mvc.addObject("meter", meter);
		return mvc;
	}
	// ------------------------------OVERWRITE-----------------------------------------

	@RequestMapping("/Admin/Meter/meterEnergyOverwrite")
	public ModelAndView meterEnergyOverwrite(@Valid @ModelAttribute("meter") MeterEnergy meter, BindingResult result) {

		if (result.hasErrors()) {
			Map<String, Object> model = prepareModel();
			return new ModelAndView("/Admin/Meter/MeterEnergyEdit", "model", model);
		}
		try {
			meterService.updateEnergy(meter);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			Map<String, Object> model = prepareModel();
			result.rejectValue("serialNumber", "error.meter", "Istnieje już licznik z takim numerem seryjnym");
			return new ModelAndView("/Admin/Meter/MeterEnergyEdit", "model", model);
		}
		return new ModelAndView("redirect:/Admin/Meter/meterEnergyList.html");
	}

	@RequestMapping("/Admin/Meter/meterWaterOverwrite")
	public ModelAndView meterWaterOverwrite(@Valid @ModelAttribute("meter") MeterWater meter, BindingResult result) {

		if (meter.getApartment() == null && meter.getIsWarmWater() == true) {
			result.rejectValue("isWarmWater", "error.meter",
					"Licznik cz�ci wsp�lnej nie może być licznikiem ciep�ej wody");
		}

		if (result.hasErrors()) {
			Map<String, Object> model = prepareModel();
			return new ModelAndView("/Admin/Meter/MeterWaterEdit", "model", model);
		}
		try {
			meterService.updateWater(meter);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			Map<String, Object> model = prepareModel();
			result.rejectValue("serialNumber", "error.meter", "Istnieje już licznik z takim numerem seryjnym");
			return new ModelAndView("/Admin/Meter/MeterWaterEdit", "model", model);
		}
		return new ModelAndView("redirect:/Admin/Meter/meterWaterList.html");
	}

	@RequestMapping("/Admin/Meter/meterGasOverwrite")
	public ModelAndView meterGasOverwrite(@Valid @ModelAttribute("meter") MeterGas meter, BindingResult result) {
		if (meter.getApartment() == null && meter.isCwu() == true) {
			result.rejectValue("cwu", "error.meter", "Licznik cz�ci wsp�lnej nie może być licznikiem CWU");
		}

		if (result.hasErrors()) {
			Map<String, Object> model = prepareModel();
			return new ModelAndView("/Admin/Meter/MeterGasEdit", "model", model);
		}
		try {
			meterService.updateGas(meter);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			Map<String, Object> model = prepareModel();
			result.rejectValue("serialNumber", "error.meter", "Istnieje już licznik z takim numerem seryjnym");
			return new ModelAndView("/Admin/Meter/MeterGasEdit", "model", model);
		}
		return new ModelAndView("redirect:/Admin/Meter/meterGasList.html");
	}

	// ------------------DELETE-------------------------------------

	@RequestMapping(value = "/Admin/Meter/meterEnergyDelete", params = { "id" })
	public ModelAndView usunLicznikEnergia(@RequestParam(value = "id") int id) {
		meterService.deleteEnergyByID(id);
		return new ModelAndView("redirect:/Admin/Meter/meterEnergyList.html");
	}

	@RequestMapping(value = "/Admin/Meter/meterWaterDelete", params = { "id" })
	public ModelAndView meterWaterDelete(@RequestParam(value = "id") int id) {
		meterService.deleteWaterByID(id);
		return new ModelAndView("redirect:/Admin/Meter/meterWaterList.html");
	}

	@RequestMapping(value = "/Admin/Meter/meterGasDelete", params = { "id" })
	public ModelAndView meterGasDelete(@RequestParam(value = "id") int id) {
		meterService.deleteGasByID(id);
		return new ModelAndView("redirect:/Admin/Meter/meterGasList.html");
	}

	// ---------------------------------private_metods-----------------------------------------

	private Map<String, Object> prepareModel() {
		Map<String, Object> model = new HashMap<String, Object>();
		List<Apartment> apartment = (apartmentService.getList());
		if (apartment.isEmpty()) {
			model.put("error", "Wprowadź przynajmniej jedno mieszkanie do bazy danych");
		} else {
			Apartment m = createNullApartment();
			apartment.add(m);

		}
		ArrayList<Boolean> tf = new ArrayList<Boolean>();
		tf.add(true);
		tf.add(false);
		model.put("tf", tf);
		model.put("apartment", apartment);
		return model;
	}

	private Apartment createNullApartment() {
		Apartment m = new Apartment();
		m.setDescription("Licznik Główny");
		m.setApartmentNumber(-1);
		m.setId(-1);
		return m;
	}
}