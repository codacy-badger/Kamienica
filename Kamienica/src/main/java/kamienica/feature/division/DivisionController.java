package kamienica.feature.division;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kamienica.core.DivisionManager;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentService;
import kamienica.feature.tenant.Tenant;
import kamienica.feature.tenant.TenantService;

@Controller
@RequestMapping("/Admin/Division")
public class DivisionController {

	@Autowired
	private ApartmentService apartmentService;
	@Autowired
	private TenantService tenantService;
	@Autowired
	private DivisionService divisionService;

//	@InitBinder
//	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		sdf.setLenient(true);
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
//	}

	@RequestMapping("/divisionRegister")
	public ModelAndView divisionRegister(@ModelAttribute("divisionForm") DivisionForm divisionForm,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		ArrayList<Tenant> tenantList = (ArrayList<Tenant>) tenantService.getCurrentTenants();
		List<Apartment> apartmentList = apartmentService.getList();
		if (tenantList.isEmpty() || apartmentList.isEmpty()) {
			model.put("error", "Brakuje danych. Upewnij się że dane dotyczące mieszkań i najemców są poprawne");
			return new ModelAndView("/Admin/Division/DivisionRegister", "model", model);
		}
		ArrayList<Division> divisionList = DivisionManager.prepareDivisionListForRegistration(tenantList,
				apartmentList);
		divisionForm.setDivisionList(divisionList);
		model.put("apartment", apartmentList);
		model.put("tenantList", tenantList);
		return new ModelAndView("/Admin/Division/DivisionRegister", "model", model);
	}

	@RequestMapping(value = "/divisionSave", method = RequestMethod.POST)
	public ModelAndView divisionSave(@ModelAttribute("divisionForm") DivisionForm divisionForm, BindingResult result) {
		LocalDate date = divisionForm.getDate();
		List<Division> divisionList = divisionForm.getDivisionList();
		List<Apartment> apartmentList = apartmentService.getList();

		if (DivisionValidator.checksumForDivision(apartmentList, divisionList)) {
			divisionService.saveList(divisionList, date);
			return new ModelAndView("redirect:/Admin/Division/divisionList.html");
		} else {
			Map<String, Object> model = new HashMap<String, Object>();
			String error = "Błąd. Sprawdź poprawność danych";
			model.put("error", error);
			model.put("apartment", apartmentList);
			model.put("tenantList", tenantService.getCurrentTenants());
			return new ModelAndView("/Admin/Division/DivisionRegister", "model", model);
		}
	}

	@RequestMapping("/divisionList")
	public ModelAndView divisionList() {
		Map<String, Object> model = new HashMap<String, Object>();
		DivisionForm divisionForm = new DivisionForm();
		divisionForm.setDivisionList((ArrayList<Division>) divisionService.getList());
		List<Tenant> tenants = tenantService.getCurrentTenants();
		List<Apartment> apartments = apartmentService.getList();
		if (!DivisionValidator.validateDivision(apartments, divisionForm.getDivisionList(), tenants)) {
			model.put("error",
					"Podział jest nieaktualny. <a href='divisionRegister.html' class='alert alert-danger'><b>Zaktualizuj dane.</b></a> ");
		}
		model.put("tenantList", tenants);
		model.put("apartment", apartments);
		model.put("divisionForm", divisionForm);
		return new ModelAndView("/Admin/Division/DivisionList", "model", model);

	}
}
