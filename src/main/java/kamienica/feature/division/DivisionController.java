package kamienica.feature.division;

import kamienica.feature.apartment.IApartmentService;
import kamienica.feature.tenant.ITenantService;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Division;
import kamienica.model.entity.Tenant;
import kamienica.model.exception.WrongDivisionInputException;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Admin/Division")
public class DivisionController {

	@Autowired
	private IApartmentService apartmentService;
	@Autowired
	private ITenantService tenantService;
	@Autowired
	private IDivisionService divisionService;

	@RequestMapping("/divisionRegister")
	public ModelAndView divisionRegister(@ModelAttribute("divisionForm") DivisionForm divisionForm,
			BindingResult result) {
		final Map<String, Object> model = new HashMap<>();
		try {
			divisionService.prepareForm(divisionForm);
		} catch (WrongDivisionInputException e) {

			model.put("error", "Brakuje danych. Upewnij się że dane dotyczące mieszkań i najemców są poprawne");
			return new ModelAndView("/Admin/Division/DivisionRegister", "model", model);
		}

		return new ModelAndView("/Admin/Division/DivisionRegister", "model", model);
	}

	@RequestMapping(value = "/divisionSave", method = RequestMethod.POST)
	public ModelAndView divisionSave(@ModelAttribute("divisionForm") DivisionForm divisionForm, final BindingResult result) {
		final LocalDate date = divisionForm.getDate();
		List<Division> divisionList = divisionForm.getDivisionList();
		List<Apartment> apartmentList = apartmentService.list();

		if (DivisionValidator.checkIfDivisionIsCorrect(apartmentList, divisionList)) {
			divisionService.saveList(divisionList, date);
			return new ModelAndView("redirect:/Admin/Division/divisionList.html");
		} else {
			Map<String, Object> model = new HashMap<>();
			String error = "Błąd. Sprawdź poprawność danych";
			model.put("error", error);
			model.put("apartment", apartmentList);
			model.put("tenantList", tenantService.getActiveTenants());
			return new ModelAndView("/Admin/Division/DivisionRegister", "model", model);
		}
	}

	@RequestMapping("/divisionList")
	public ModelAndView divisionList() {
		Map<String, Object> model = new HashMap<>();
		DivisionForm divisionForm = new DivisionForm();
		divisionForm.setDivisionList(divisionService.getList());
		List<Tenant> tenants = tenantService.getActiveTenants();
		List<Apartment> apartments = apartmentService.list();
		model.put("tenantList", tenants);
		model.put("apartment", apartments);
		model.put("divisionForm", divisionForm);
		return new ModelAndView("/Admin/Division/DivisionList", "model", model);

	}
	
	@RequestMapping(value = "/divisionRest", method = RequestMethod.GET)
	public ModelAndView restPage() {

		return new ModelAndView("/Admin/Division/DivisionRest");

	}
}
