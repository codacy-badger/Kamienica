package kamienica.controller.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kamienica.initBinder.ApartmentIB;
import kamienica.model.Apartment;
import kamienica.service.ApartmentService;

@Controller
public class ApartmentController {

	@Autowired
	private ApartmentService apartmentService;

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Apartment.class, new ApartmentIB(this.apartmentService));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

//	@Autowired
//	private Validator validator;
//
//	public void setValidator(Validator validator) {
//		this.validator = validator;
//	}

	@RequestMapping("/Admin/Apartment/apartmentRegister")
	public ModelAndView ApartmentRegister(@ModelAttribute("apartment") Apartment apartment, BindingResult result) {
		return new ModelAndView("/Admin/Apartment/ApartmentRegister");
	}

	@RequestMapping("/Admin/Apartment/apartmentSave")
	public ModelAndView save(@Valid @ModelAttribute("apartment") Apartment apartment, BindingResult result) {

		// validator.validate(apartment, result);
		if (result.hasErrors()) {
			System.out.println(result.toString());
			return new ModelAndView("/Admin/Apartment/ApartmentRegister");
		}

		try {
			apartmentService.save(apartment);
		} catch (ConstraintViolationException e) {
			result.rejectValue("apartmentNumber", "error.apartment", "Istnieje już taki numer mieszkania w bazie");
			return new ModelAndView("/Admin/Apartment/ApartmentRegister");
		}

		return new ModelAndView("redirect:/Admin/Apartment/apartmentList.html");
	}

	@RequestMapping(value = "/Admin/Apartment/apartmentEdit", params = { "id" })
	public ModelAndView apartmentEdit(@RequestParam(value = "id") int id,
			@ModelAttribute("apartment") Apartment apartment) {
		Map<String, Object> model = new HashMap<String, Object>();
		Apartment apToEdit = apartmentService.getById(id);
		System.out.println("PK:  " + id);
		apartment.setIntercom(apToEdit.getIntercom());
		apartment.setId(apToEdit.getId());
		apartment.setApartmentNumber(apToEdit.getApartmentNumber());
		apartment.setDescription(apToEdit.getDescription());
		model.put("apartment", apToEdit);
		return new ModelAndView("/Admin/Apartment/ApartmentEdit");
	}

	@RequestMapping("/Admin/Apartment/apartmentOverwrite")
	public ModelAndView apartmentOverwrite(@Valid @ModelAttribute("apartment") Apartment apartment,
			BindingResult result) {

//		validator.validate(apartment, result);
		if (result.hasErrors()) {

			return new ModelAndView("/Admin/Apartment/ApartmentEdit");
		}

		try {
			apartmentService.update(apartment);
		} catch (ConstraintViolationException e) {
			result.rejectValue("apartmentNumber", "error.apartment", "Istnieje już taki numer mieszkania w bazie");
			return new ModelAndView("/Admin/Apartment/ApartmentEdit");
		}
		return new ModelAndView("redirect:/Admin/Apartment/apartmentList.html");
	}

	@RequestMapping(value = "/Admin/Apartment/apartmentDelete", params = { "id" })
	public ModelAndView apartmentDelete(@RequestParam(value = "id") int id) {
		apartmentService.deleteByID(id);
		return new ModelAndView("redirect:/Admin/Apartment/apartmentList.html");
	}

	@RequestMapping("/Admin/Apartment/apartmentList")
	public ModelAndView apartmentList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("apartment", apartmentService.getList());
		return new ModelAndView("/Admin/Apartment/ApartmentList", model);

	}

}
