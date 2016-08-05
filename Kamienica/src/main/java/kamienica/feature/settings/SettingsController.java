package kamienica.feature.settings;

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

@Controller
@RequestMapping("/Admin/Settings")
public class SettingsController {

	@Autowired
	private SettingsService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("settings", service.getSettings());
		return new ModelAndView("/Admin/Settings/SettingsList", "model", model);

	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editOrNew(@ModelAttribute("settings") Settings settings, BindingResult result) {

		return new ModelAndView("/Admin/Settings/SettingsRegister");

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("settings") Settings settings, BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("/Admin/Settings/SettingsRegister");
		}
		service.save(settings);
		return new ModelAndView("redirect:/Admin/Settings/list.html");
	}
}
