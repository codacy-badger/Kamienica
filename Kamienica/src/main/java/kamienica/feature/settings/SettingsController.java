package kamienica.feature.settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kamienica.core.util.WaterHeatingSystem;

@Controller
@RequestMapping("/Admin/Settings")
public class SettingsController {

	private ArrayList<Boolean> values = new ArrayList<>(Arrays.asList(true, false));
	private ArrayList<String> labels = new ArrayList<>(Arrays.asList("Tak", "Nie"));
	@Autowired
	private SettingsService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@ModelAttribute("settings") Settings settings, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println(service.getSettings());
		model.put("settings", service.getSettings());
		return new ModelAndView("/Admin/Settings/SettingsList", "model", model);

	}

	// @RequestMapping(value = "/apartmentEdit", method = RequestMethod.GET)
	// public ModelAndView apartmentEdit(@RequestParam(value = "id") Long id,
	// @ModelAttribute("apartment") Apartment apartment) {
	// ModelAndView mvc = new
	// ModelAndView("/Admin/Apartment/ApartmentRegister");
	// mvc.addObject("url", "/Admin/Apartment/apartmentOverwrite.html");
	// mvc.addObject("apartment", apartmentService.getById(id));
	// return mvc;
	// }

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editOrNew(@ModelAttribute("settings") Settings settings, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("item", service.getSettings());
		model.put("values", values);
		model.put("labels", labels);
		model.put("cwu", WaterHeatingSystem.values());
		return new ModelAndView("/Admin/Settings/SettingsRegister", "model", model);

	}
	// \
	// @RequestMapping(value = "/edit", method = RequestMethod.GET)
	// public ModelAndView editOrNew(@ModelAttribute("settings") Settings
	// settings, BindingResult result) {
	// Map<String, Object> model = new HashMap<String, Object>();
	// model.put("values", values);
	// model.put("labels", labels);
	// return new ModelAndView("/Admin/Settings/SettingsRegister", "model",
	// model);
	//
	// }

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("settings") Settings settings, BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("/Admin/Settings/SettingsRegister");
		}
		System.out.println("===============");
		System.out.println(settings);
		service.save(settings);
		return new ModelAndView("redirect:/Admin/Settings/list.html");
	}
}
