//package kamienica.feature.settings;
//
//import kamienica.model.entity.Settings;
//import kamienica.model.enums.WaterHeatingSystem;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.validation.Valid;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/owner/Settings")
//public class SettingsController {
//
//	private ArrayList<Boolean> values = new ArrayList<>(Arrays.asList(true, false));
//	private ArrayList<String> labels = new ArrayList<>(Arrays.asList("Tak", "Nie"));
//	@Autowired
//	private ISettingsService service;
//
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public ModelAndView list(@ModelAttribute("settings") Settings settings, BindingResult result) {
//		Map<String, Object> model = new HashMap<>();
//		model.put("settings", service.list());
//		return new ModelAndView("/owner/Settings/SettingsList", "model", model);
//
//	}
//
//	// @RequestMapping(value = "/apartmentEdit", method = RequestMethod.GET)
//	// public ModelAndView apartmentEdit(@RequestParam(value = "id") Long id,
//	// @ModelAttribute("apartment") Apartment apartment) {
//	// ModelAndView mvc = new
//	// ModelAndView("/owner/Apartment/ApartmentRegister");
//	// mvc.addObject("url", "/owner/Apartment/apartmentOverwrite.html");
//	// mvc.addObject("apartment", apartmentService.getById(id));
//	// return mvc;
//	// }
//
//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
//	public ModelAndView editOrNew(@ModelAttribute("settings") Settings settings, BindingResult result) {
//		Map<String, Object> model = new HashMap<>();
//		model.put("item", service.list());
//		model.put("values", values);
//		model.put("labels", labels);
//		model.put("cwu", WaterHeatingSystem.values());
//		return new ModelAndView("/owner/Settings/SettingsRegister", "model", model);
//
//	}
//	// \
//	// @RequestMapping(value = "/edit", method = RequestMethod.GET)
//	// public ModelAndView editOrNew(@ModelAttribute("settings") Settings
//	// settings, BindingResult result) {
//	// Map<String, Object> model = new HashMap<String, Object>();
//	// model.put("values", values);
//	// model.put("labels", labels);
//	// return new ModelAndView("/owner/Settings/SettingsRegister", "model",
//	// model);
//	//
//	// }
//
//	@RequestMapping(value = "/save", method = RequestMethod.POST)
//	public ModelAndView save(@Valid @ModelAttribute("settings") Settings settings, BindingResult result) {
//
//		if (result.hasErrors()) {
//			return new ModelAndView("/owner/Settings/SettingsRegister");
//		}
//		service.save(settings);
//		return new ModelAndView("redirect:/owner/Settings/list.html");
//	}
//}
