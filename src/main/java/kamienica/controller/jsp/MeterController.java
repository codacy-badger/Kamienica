package kamienica.controller.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MeterController {

//	@Autowired
//	private ApartmentService apartmentService;
//	@Autowired
//	private MeterService meterService;
//
//	
//	private final String DUPLICATE_SERIAL = "Istnieje już w bazie licznik z takim numerem seryjnym";
//	private final String WARM_CWU = "Licznik Główny nie może być licznikiem CWU bądź Ciepłej Wody";
//	private final String MAIN_EXISTS = "Istnieje już w bazie licznik główny";
//	private final String DUPLICATE_DESC = "Opis musi być unikatowy";
//	// ------------------Register-------------------------------------
//
//	@RequestMapping("/Admin/Meter/meterEnergyRegister")
//	public ModelAndView meterEnergyRegister(@ModelAttribute("meter") MeterEnergy meter, BindingResult result) {
//		Map<String, Object> model = prepareModel();
//		model.put("url", "/Admin/Meter/meterEnergySave.html");
//		return new ModelAndView("/Admin/Meter/MeterEnergyRegister", "model", model);
//	}
//
//	@RequestMapping("/Admin/Meter/meterWaterRegister")
//	public ModelAndView meterWaterRegister(@ModelAttribute("meter") MeterWater meter, BindingResult result) {
//		Map<String, Object> model = prepareModel();
//		model.put("url", "/Admin/Meter/meterWaterSave.html");
//		return new ModelAndView("/Admin/Meter/MeterWaterRegister", "model", model);
//	}
//
//	@RequestMapping("/Admin/Meter/meterGasRegister")
//	public ModelAndView meterGasRegister(@ModelAttribute("meter") MeterGas meter, BindingResult result) {
//		Map<String, Object> model = prepareModel();
//		model.put("url", "/Admin/Meter/meterGasSave.html");
//		return new ModelAndView("/Admin/Meter/MeterGasRegister", "model", model);
//	}
//
//	// ----------------------------SAVE------------------------------------------
//
//	@RequestMapping(value = "/Admin/Meter/meterEnergySave", method = RequestMethod.POST)
//	public ModelAndView meterEnergySave(@Valid @ModelAttribute("meter") MeterEnergy meter, BindingResult result) {
//
//		if (meter.main && meterService.ifMainExists(Media.ENERGY)) {
//			result.rejectValue("apartment", "error.meter", MAIN_EXISTS);
//		}
//
//		if (result.hasErrors()) {
//			Map<String, Object> model = prepareModel();
//			model.put("url", "/Admin/Meter/meterEnergySave.html");
//			return new ModelAndView("/Admin/Meter/MeterEnergyRegister", "model", model);
//		}
//		try {
//			meterService.save(meter, Media.ENERGY);
//			// meterService.saveEnergy(meter);
//		} catch (ConstraintViolationException e) {
//			Map<String, Object> model = prepareModel();
//			if (e.getCause().toString().contains("desc")) {
//				result.rejectValue("description", "error.meter", DUPLICATE_DESC);
//			} else {
//				result.rejectValue("serialNumber", "error.meter", DUPLICATE_SERIAL);
//			}
//
//			model.put("url", "/Admin/Meter/meterEnergySave.html");
//
//			return new ModelAndView("/Admin/Meter/MeterEnergyRegister", "model", model);
//
//		}
//		return new ModelAndView("redirect:/Admin/Meter/meterEnergyList.html");
//	}
//
//	@RequestMapping(value = "/Admin/Meter/meterWaterSave", method = RequestMethod.POST)
//	public ModelAndView meterWaterSave(@Valid @ModelAttribute("meter") MeterWater meter, BindingResult result) {
//		if (meter.getApartment() == null && meter.getIsWarmWater()) {
//			result.rejectValue("isWarmWater", "error.meter", WARM_CWU);
//		}
//
//		if (meter.main && meterService.ifMainExists(Media.WATER)) {
//			result.rejectValue("apartment", "error.meter", MAIN_EXISTS);
//		}
//
//		if (result.hasErrors()) {
//			Map<String, Object> model = prepareModel();
//			model.put("url", "/Admin/Meter/meterWaterSave.html");
//			return new ModelAndView("/Admin/Meter/MeterWaterRegister", "model", model);
//		}
//		try {
//			// meterService.saveWater(meter);
//			meterService.save(meter, Media.WATER);
//		} catch (ConstraintViolationException e) {
//			Map<String, Object> model = prepareModel();
//			if (e.getCause().toString().contains("desc")) {
//				result.rejectValue("description", "error.meter", DUPLICATE_DESC);
//			} else {
//				result.rejectValue("serialNumber", "error.meter", DUPLICATE_SERIAL);
//			}
//			model.put("url", "/Admin/Meter/meterWaterSave.html");
//
//			return new ModelAndView("/Admin/Meter/MeterWaterRegister", "model", model);
//
//		}
//		return new ModelAndView("redirect:/Admin/Meter/meterWaterList.html");
//	}
//
//	@RequestMapping(value = "/Admin/Meter/meterGasSave", method = RequestMethod.POST)
//	public ModelAndView meterGasSave(@Valid @ModelAttribute("meter") MeterGas meter, BindingResult result) {
//
//		if (meter.getApartment() == null && meter.isCwu()) {
//			result.rejectValue("cwu", "error.meter", WARM_CWU);
//		}
//		if (meter.main && meterService.ifMainExists(Media.GAS)) {
//			result.rejectValue("apartment", "error.meter", MAIN_EXISTS);
//		}
//		if (result.hasErrors()) {
//			Map<String, Object> model = prepareModel();
//			model.put("url", "/Admin/Meter/meterGasSave.html");
//			return new ModelAndView("/Admin/Meter/MeterGasRegister", "model", model);
//		}
//		try {
//			meterService.save(meter, Media.GAS);
//			// meterService.saveGas(meter);
//		} catch (ConstraintViolationException e) {
//			Map<String, Object> model = prepareModel();
//			if (e.getCause().toString().contains("desc")) {
//				result.rejectValue("description", "error.meter", DUPLICATE_DESC);
//			} else {
//				result.rejectValue("serialNumber", "error.meter", DUPLICATE_SERIAL);
//			}
//			model.put("url", "/Admin/Meter/meterGasSave.html");
//
//			return new ModelAndView("/Admin/Meter/MeterGasRegister", "model", model);
//
//		}
//
//		return new ModelAndView("redirect:/Admin/Meter/meterGasList.html");
//	}
//
//	// -----------------------------LIST-----------------------------------
//
//	@RequestMapping("/Admin/Meter/meterEnergyList")
//	public ModelAndView meterEnergyList() {
//		Map<String, Object> model = new HashMap<>();
//
//		model.put("meter", meterService.getList(Media.ENERGY));
//		return new ModelAndView("/Admin/Meter/MeterEnergyList", model);
//
//	}
//
//	@RequestMapping("/Admin/Meter/meterWaterList")
//	public ModelAndView meterWaterList() {
//		Map<String, Object> model = new HashMap<>();
//		model.put("meter", meterService.getList(Media.WATER));
//		return new ModelAndView("/Admin/Meter/MeterWaterList", model);
//
//	}
//
//	@RequestMapping("/Admin/Meter/meterGasList")
//	public ModelAndView meterGasList() {
//		Map<String, Object> model = new HashMap<>();
//		model.put("meter", meterService.getList(Media.GAS));
//		return new ModelAndView("/Admin/Meter/MeterGasList", model);
//
//	}
//
//	// ----------------------EDIT----------------------------------------
//
//	@RequestMapping(value = "/Admin/Meter/meterEnergyEdit")
//	public ModelAndView meterEnergyEdit(@RequestParam(value = "id") Long id,
//			@ModelAttribute("meter") MeterEnergy meter) {
//		Map<String, Object> model = prepareModel();
//		model.put("url", "/Admin/Meter/meterEnergyOverwrite.html");
//		return new ModelAndView("/Admin/Meter/MeterEnergyRegister", "model", model).addObject("meter",
//				meterService.getById(id, Media.ENERGY));
//
//	}
//
//	@RequestMapping(value = "/Admin/Meter/meterWaterEdit")
//	public ModelAndView meterWaterEdit(@RequestParam(value = "id") Long id) {
//		Map<String, Object> model = prepareModel();
//		model.put("url", "/Admin/Meter/meterWaterOverwrite.html");
//		return new ModelAndView("/Admin/Meter/MeterWaterRegister", "model", model).addObject("meter",
//				meterService.getById(id, Media.WATER));
//
//	}
//
//	@RequestMapping(value = "/Admin/Meter/meterGasEdit")
//	public ModelAndView meterGasEdit(@RequestParam(value = "id") Long id) {
//		Map<String, Object> model = prepareModel();
//		model.put("url", "/Admin/Meter/meterGasOverwrite.html");
//		return new ModelAndView("/Admin/Meter/MeterGasRegister", "model", model).addObject("meter",
//				meterService.getById(id, Media.GAS));
//
//	}
//	// ------------------------------OVERWRITE-----------------------------------------
//
//	@RequestMapping(value = "/Admin/Meter/meterEnergyOverwrite", method = RequestMethod.POST)
//	public ModelAndView meterEnergyOverwrite(@Valid @ModelAttribute("meter") MeterEnergy meter, BindingResult result) {
//		if (result.hasErrors()) {
//			Map<String, Object> model = prepareModel();
//			model.put("url", "/Admin/Meter/meterEnergyOverwrite.html");
//			return new ModelAndView("/Admin/Meter/MeterEnergyRegister", "model", model);
//		}
//		try {
//			meterService.update(meter, Media.ENERGY);
//			// meterService.updateEnergy(meter);
//		} catch (org.springframework.dao.DataIntegrityViolationException e) {
//
//			Map<String, Object> model = prepareModel();
//			model.put("url", "/Admin/Meter/meterEnergyOverwrite.html");
//			if (e.getRootCause().toString().contains("desc")) {
//				result.rejectValue("description", "error.meter", DUPLICATE_DESC);
//			} else {
//				result.rejectValue("serialNumber", "error.meter", DUPLICATE_SERIAL);
//			}
//			return new ModelAndView("/Admin/Meter/MeterEnergyRegister", "model", model);
//		}
//		return new ModelAndView("redirect:/Admin/Meter/meterEnergyList.html");
//	}
//
//	@RequestMapping(value = "/Admin/Meter/meterWaterOverwrite", method = RequestMethod.POST)
//	public ModelAndView meterWaterOverwrite(@Valid @ModelAttribute("meter") MeterWater meter, BindingResult result) {
//
//		if (meter.getApartment() == null && meter.getIsWarmWater()) {
//			result.rejectValue("isWarmWater", "error.meter", WARM_CWU);
//		}
//
//		if (result.hasErrors()) {
//			Map<String, Object> model = prepareModel();
//			model.put("url", "/Admin/Meter/meterWaterOverwrite.html");
//			return new ModelAndView("/Admin/Meter/MeterWaterRegister", "model", model);
//		}
//		try {
//			meterService.update(meter, Media.WATER);
//			// meterService.updateWater(meter);
//		} catch (org.springframework.dao.DataIntegrityViolationException e) {
//			Map<String, Object> model = prepareModel();
//			model.put("url", "/Admin/Meter/meterWaterOverwrite.html");
//			if (e.getRootCause().toString().contains("desc")) {
//				result.rejectValue("description", "error.meter", DUPLICATE_DESC);
//			} else {
//				result.rejectValue("serialNumber", "error.meter", DUPLICATE_SERIAL);
//			}
//			return new ModelAndView("/Admin/Meter/MeterWaterRegister", "model", model);
//		}
//		return new ModelAndView("redirect:/Admin/Meter/meterWaterList.html");
//	}
//
//	@RequestMapping(value = "/Admin/Meter/meterGasOverwrite", method = RequestMethod.POST)
//	public ModelAndView meterGasOverwrite(@Valid @ModelAttribute("meter") MeterGas meter, BindingResult result) {
//		if (meter.getApartment() == null && meter.isCwu()) {
//			result.rejectValue("cwu", "error.meter", WARM_CWU);
//		}
//		if (result.hasErrors()) {
//			Map<String, Object> model = prepareModel();
//			model.put("url", "/Admin/Meter/meterGasOverwrite.html");
//			return new ModelAndView("/Admin/Meter/MeterGasRegister", "model", model);
//		}
//		try {
//			meterService.update(meter, Media.GAS);
//			// meterService.updateGas(meter);
//		} catch (org.springframework.dao.DataIntegrityViolationException e) {
//			Map<String, Object> model = prepareModel();
//			model.put("url", "/Admin/Meter/meterGasOverwrite.html");
//			if (e.getRootCause().toString().contains("desc")) {
//				result.rejectValue("description", "error.meter", DUPLICATE_DESC);
//			} else {
//				result.rejectValue("serialNumber", "error.meter", DUPLICATE_SERIAL);
//			}
//			return new ModelAndView("/Admin/Meter/MeterGasRegister", "model", model);
//		}
//		return new ModelAndView("redirect:/Admin/Meter/meterGasList.html");
//	}
//
//	// ------------------DELETE-------------------------------------
//
//	@RequestMapping(value = "/Admin/Meter/meterEnergyDelete", params = { "id" })
//	public ModelAndView usunLicznikEnergia(@RequestParam(value = "id") Long id) {
//		// meterService.deleteEnergyByID(id);
//		meterService.delete(id, Media.ENERGY);
//		return new ModelAndView("redirect:/Admin/Meter/meterEnergyList.html");
//	}
//
//	@RequestMapping(value = "/Admin/Meter/meterWaterDelete", params = { "id" })
//	public ModelAndView meterWaterDelete(@RequestParam(value = "id") Long id) {
//		// meterService.deleteWaterByID(id);
//		meterService.delete(id, Media.WATER);
//		return new ModelAndView("redirect:/Admin/Meter/meterWaterList.html");
//	}
//
//	@RequestMapping(value = "/Admin/Meter/meterGasDelete", params = { "id" })
//	public ModelAndView meterGasDelete(@RequestParam(value = "id") Long id) {
//		// meterService.deleteGasByID(id);
//		meterService.delete(id, Media.GAS);
//		return new ModelAndView("redirect:/Admin/Meter/meterGasList.html");
//	}
	

	@RequestMapping(value = "/Admin/Meter/energy")
	public ModelAndView energyRest() {

		return new ModelAndView("/Admin/Meter/energy");
	}
	
	@RequestMapping(value = "/Admin/Meter/gas")
	public ModelAndView gasRest() {

		return new ModelAndView("/Admin/Meter/gas");
	}
	
	@RequestMapping(value = "/Admin/Meter/water")
	public ModelAndView waterRest() {

		return new ModelAndView("/Admin/Meter/water");
	}
	

	
}