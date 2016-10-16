package kamienica.feature.apartment;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/Admin/Apartment")
public class ApartmentController {

	@Autowired
	private ApartmentService apartmentService;
	private final String DUPLICATE_EROR = "Istnieje już taki numer mieszkania w bazie";

	@RequestMapping(value = "/apartmentRegister", method = RequestMethod.GET)
	public ModelAndView ApartmentRegister(@ModelAttribute("apartment") Apartment apartment, BindingResult result) {
		return new ModelAndView("/Admin/Apartment/ApartmentRegister").addObject("url",
				"/Admin/Apartment/apartmentSave.html");
	}

	@RequestMapping(value = "/apartmentSave", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("apartment") Apartment apartment, BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("/Admin/Apartment/ApartmentRegister");
		}

		try {
			apartmentService.save(apartment);
		} catch (ConstraintViolationException e) {
			result.rejectValue("apartmentNumber", "error.apartment", DUPLICATE_EROR);
			return new ModelAndView("/Admin/Apartment/ApartmentRegister");
		}

		return new ModelAndView("redirect:/Admin/Apartment/apartmentList.html");
	}

	@RequestMapping(value = "/apartmentEdit", method = RequestMethod.GET)
	public ModelAndView apartmentEdit(@RequestParam(value = "id") Long id,
			@ModelAttribute("apartment") Apartment apartment) {
		ModelAndView mvc = new ModelAndView("/Admin/Apartment/ApartmentRegister");
		mvc.addObject("url", "/Admin/Apartment/apartmentOverwrite.html");
		mvc.addObject("apartment", apartmentService.getById(id));
		return mvc;
	}

	@RequestMapping(value = "/apartmentOverwrite", method = RequestMethod.POST)
	public ModelAndView apartmentOverwrite(@Valid @ModelAttribute("apartment") Apartment apartment,
			BindingResult result) {
		if (result.hasErrors()) {

			return new ModelAndView("/Admin/Apartment/ApartmentRegister");
		}

		try {
			apartmentService.update(apartment);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			result.rejectValue("apartmentNumber", "error.apartment", DUPLICATE_EROR);
			return new ModelAndView("/Admin/Apartment/ApartmentRegister");
		}
		return new ModelAndView("redirect:/Admin/Apartment/apartmentList.html");
	}

	@RequestMapping(value = "/apartmentDelete", method = RequestMethod.GET)
	public ModelAndView apartmentDelete(@RequestParam(value = "id") Long id) {
		apartmentService.deleteByID(id);
		return new ModelAndView("redirect:/Admin/Apartment/apartmentList.html");
	}

	@RequestMapping(value = "/apartmentList", method = RequestMethod.GET)
	public ModelAndView apartmentList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("apartment", apartmentService.getList());
		return new ModelAndView("/Admin/Apartment/ApartmentList", model);

	}

	@RequestMapping(value = "/apartmentList2", method = RequestMethod.GET)
	public ModelAndView apartmentList2() {

		return new ModelAndView("/Admin/Apartment/ApartmentList2");

	}

}
