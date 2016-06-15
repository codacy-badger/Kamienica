package kamienica.feature.meter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentService;

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

	private final String DUPLICATE = "Istnieje już w bazie licznik z takim numerem seryjnym";
	private final String WARM_CWU = "Licznik Główny nie może być licznikiem CWU bądź Ciepłej Wody";

	// ------------------Register-------------------------------------

	@RequestMapping("/Admin/Meter/meterEnergyRegister")
	public ModelAndView meterEnergyRegister(@ModelAttribute("meter") MeterEnergy meter, BindingResult result) {
		Map<String, Object> model = prepareModel();
		model.put("url", "/Admin/Meter/meterEnergySave.html");
		return new ModelAndView("/Admin/Meter/MeterEnergyRegister", "model", model);
	}

	@RequestMapping("/Admin/Meter/meterWaterRegister")
	public ModelAndView meterWaterRegister(@ModelAttribute("meter") MeterWater meter, BindingResult result) {
		Map<String, Object> model = prepareModel();
		model.put("url", "/Admin/Meter/meterWaterSave.html");
		return new ModelAndView("/Admin/Meter/MeterWaterRegister", "model", model);
	}

	@RequestMapping("/Admin/Meter/meterGasRegister")
	public ModelAndView meterGasRegister(@ModelAttribute("meter") MeterGas meter, BindingResult result) {
		Map<String, Object> model = prepareModel();
		model.put("url", "/Admin/Meter/meterGasSave.html");
		return new ModelAndView("/Admin/Meter/MeterGasRegister", "model", model);
	}

	// ----------------------------SAVE------------------------------------------

	@RequestMapping(value = "/Admin/Meter/meterEnergySave", method = RequestMethod.POST)
	public ModelAndView meterEnergySave(@Valid @ModelAttribute("meter") MeterEnergy meter, BindingResult result) {

		if (result.hasErrors()) {
			Map<String, Object> model = prepareModel();
			model.put("url", "/Admin/Meter/meterEnergySave.html");
			return new ModelAndView("/Admin/Meter/MeterEnergyRegister", "model", model);
		}
		try {
			meterService.saveEnergy(meter);
		} catch (ConstraintViolationException e) {
			Map<String, Object> model = prepareModel();
			model.put("url", "/Admin/Meter/meterEnergySave.html");
			result.rejectValue("serialNumber", "error.meter", DUPLICATE);
			return new ModelAndView("/Admin/Meter/MeterEnergyRegister", "model", model);

		}
		return new ModelAndView("redirect:/Admin/Meter/meterEnergyList.html");
	}

	@RequestMapping(value = "/Admin/Meter/meterWaterSave", method = RequestMethod.POST)
	public ModelAndView meterWaterSave(@Valid @ModelAttribute("meter") MeterWater meter, BindingResult result) {
		if (meter.getApartment() == null && meter.getIsWarmWater() == true) {
			result.rejectValue("isWarmWater", "error.meter", WARM_CWU);
		}

		if (result.hasErrors()) {
			Map<String, Object> model = prepareModel();
			model.put("url", "/Admin/Meter/meterWaterSave.html");
			return new ModelAndView("/Admin/Meter/MeterWaterRegister", "model", model);
		}
		try {
			meterService.saveWater(meter);
		} catch (ConstraintViolationException e) {
			Map<String, Object> model = prepareModel();
			model.put("url", "/Admin/Meter/meterWaterSave.html");
			result.rejectValue("serialNumber", "error.meter", DUPLICATE);
			return new ModelAndView("/Admin/Meter/MeterWaterRegister", "model", model);

		}
		return new ModelAndView("redirect:/Admin/Meter/meterWaterList.html");
	}

	@RequestMapping(value = "/Admin/Meter/meterGasSave", method = RequestMethod.POST)
	public ModelAndView meterGasSave(@Valid @ModelAttribute("meter") MeterGas meter, BindingResult result) {

		if (meter.getApartment() == null && meter.isCwu() == true) {
			result.rejectValue("cwu", "error.meter", WARM_CWU);
		}
		if (result.hasErrors()) {
			Map<String, Object> model = prepareModel();
			model.put("url", "/Admin/Meter/meterGasSave.html");
			return new ModelAndView("/Admin/Meter/MeterGasRegister", "model", model);
		}
		try {
			meterService.saveGas(meter);
		} catch (ConstraintViolationException e) {
			Map<String, Object> model = prepareModel();
			model.put("url", "/Admin/Meter/meterGasSave.html");
			result.rejectValue("serialNumber", "error.meter", DUPLICATE);
			return new ModelAndView("/Admin/Meter/MeterGasRegister", "model", model);

		}

		return new ModelAndView("redirect:/Admin/Meter/meterGasList.html");
	}

	// -----------------------------LIST-----------------------------------

	@RequestMapping("/Admin/Meter/meterEnergyList")
	public ModelAndView meterEnergyList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("meter", meterService.getEnergyList());
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

	@RequestMapping(value = "/Admin/Meter/meterEnergyEdit")
	public ModelAndView meterEnergyEdit(@RequestParam(value = "id") Long id,
			@ModelAttribute("meter") MeterEnergy meter) {
		Map<String, Object> model = prepareModel();
		model.put("url", "/Admin/Meter/meterEnergyOverwrite.html");
		return new ModelAndView("/Admin/Meter/MeterEnergyRegister", "model", model).addObject("meter",
				meterService.getEnergyByID(id));

	}

	@RequestMapping(value = "/Admin/Meter/meterWaterEdit")
	public ModelAndView meterWaterEdit(@RequestParam(value = "id") Long id) {
		Map<String, Object> model = prepareModel();
		model.put("url", "/Admin/Meter/meterWaterOverwrite.html");
		return new ModelAndView("/Admin/Meter/MeterWaterRegister", "model", model).addObject("meter",
				meterService.getWaterByID(id));

	}

	@RequestMapping(value = "/Admin/Meter/meterGasEdit")
	public ModelAndView meterGasEdit(@RequestParam(value = "id") Long id) {
		Map<String, Object> model = prepareModel();
		model.put("url", "/Admin/Meter/meterGasOverwrite.html");
		return new ModelAndView("/Admin/Meter/MeterGasRegister", "model", model).addObject("meter",
				meterService.getGasByID(id));

	}
	// ------------------------------OVERWRITE-----------------------------------------

	@RequestMapping(value = "/Admin/Meter/meterEnergyOverwrite", method = RequestMethod.POST)
	public ModelAndView meterEnergyOverwrite(@Valid @ModelAttribute("meter") MeterEnergy meter, BindingResult result) {
		if (result.hasErrors()) {
			Map<String, Object> model = prepareModel();
			model.put("url", "/Admin/Meter/meterEnergyOverwrite.html");
			return new ModelAndView("/Admin/Meter/MeterEnergyRegister", "model", model);
		}
		try {
			meterService.updateEnergy(meter);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			Map<String, Object> model = prepareModel();
			model.put("url", "/Admin/Meter/meterEnergyOverwrite.html");
			result.rejectValue("serialNumber", "error.meter", DUPLICATE);
			return new ModelAndView("/Admin/Meter/MeterEnergyRegister", "model", model);
		}
		return new ModelAndView("redirect:/Admin/Meter/meterEnergyList.html");
	}

	@RequestMapping(value = "/Admin/Meter/meterWaterOverwrite", method = RequestMethod.POST)
	public ModelAndView meterWaterOverwrite(@Valid @ModelAttribute("meter") MeterWater meter, BindingResult result) {

		if (meter.getApartment() == null && meter.getIsWarmWater() == true) {
			result.rejectValue("isWarmWater", "error.meter", WARM_CWU);
		}

		if (result.hasErrors()) {
			Map<String, Object> model = prepareModel();
			model.put("url", "/Admin/Meter/meterWaterOverwrite.html");
			return new ModelAndView("/Admin/Meter/MeterWaterRegister", "model", model);
		}
		try {
			meterService.updateWater(meter);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			Map<String, Object> model = prepareModel();
			model.put("url", "/Admin/Meter/meterWaterOverwrite.html");
			result.rejectValue("serialNumber", "error.meter", DUPLICATE);
			return new ModelAndView("/Admin/Meter/MeterWaterRegister", "model", model);
		}
		return new ModelAndView("redirect:/Admin/Meter/meterWaterList.html");
	}

	@RequestMapping(value = "/Admin/Meter/meterGasOverwrite", method = RequestMethod.POST)
	public ModelAndView meterGasOverwrite(@Valid @ModelAttribute("meter") MeterGas meter, BindingResult result) {
		if (meter.getApartment() == null && meter.isCwu() == true) {
			result.rejectValue("cwu", "error.meter", WARM_CWU);
		}
		if (result.hasErrors()) {
			Map<String, Object> model = prepareModel();
			model.put("url", "/Admin/Meter/meterGasOverwrite.html");
			return new ModelAndView("/Admin/Meter/MeterGasRegister", "model", model);
		}
		try {
			meterService.updateGas(meter);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			Map<String, Object> model = prepareModel();
			model.put("url", "/Admin/Meter/meterGasOverwrite.html");
			result.rejectValue("serialNumber", "error.meter", DUPLICATE);
			return new ModelAndView("/Admin/Meter/MeterGasRegister", "model", model);
		}
		return new ModelAndView("redirect:/Admin/Meter/meterGasList.html");
	}

	// ------------------DELETE-------------------------------------

	@RequestMapping(value = "/Admin/Meter/meterEnergyDelete", params = { "id" })
	public ModelAndView usunLicznikEnergia(@RequestParam(value = "id") Long id) {
		meterService.deleteEnergyByID(id);
		return new ModelAndView("redirect:/Admin/Meter/meterEnergyList.html");
	}

	@RequestMapping(value = "/Admin/Meter/meterWaterDelete", params = { "id" })
	public ModelAndView meterWaterDelete(@RequestParam(value = "id") Long id) {
		meterService.deleteWaterByID(id);
		return new ModelAndView("redirect:/Admin/Meter/meterWaterList.html");
	}

	@RequestMapping(value = "/Admin/Meter/meterGasDelete", params = { "id" })
	public ModelAndView meterGasDelete(@RequestParam(value = "id") Long id) {
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
		m.setId(-1L);
		return m;
	}
}