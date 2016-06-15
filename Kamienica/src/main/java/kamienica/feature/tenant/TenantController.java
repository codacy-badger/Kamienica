package kamienica.feature.tenant;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentService;

@Controller
@RequestMapping("/Admin/Tenant")
public class TenantController {

	@Autowired
	private ApartmentService apartmentService;
	@Autowired
	private TenantService tenantService;

//	@InitBinder
//	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		sdf.setLenient(true);
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
//	}

	@Autowired
	private Validator validator;

	@RequestMapping("/tenantRegister")
	public ModelAndView tenantRegister(@ModelAttribute("tenant") Tenant tenant, BindingResult result) {
		Map<String, Object> model = prepareTenantModel();
		model.put("url", "/Admin/Tenant/tenantSave.html");
		return new ModelAndView("/Admin/Tenant/TenantRegister", "model", model);
	}

	@RequestMapping(value = "/tenantSave", method = RequestMethod.POST)
	public ModelAndView tenantSave(@Valid @ModelAttribute("tenant") Tenant tenant, BindingResult result) {
		TenantValidator.validateTenant(tenant, result);

		if (result.hasErrors()) {
			Map<String, Object> model = prepareTenantModel();
			return new ModelAndView("/Admin/Tenant/TenantRegister", "model", model);
		}

		tenantService.saveTenant(tenant);
		return new ModelAndView("redirect:/Admin/Tenant/tenantList.html");
	}

	@RequestMapping("/tenantList")
	public ModelAndView tenantList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("tenant", tenantService.getList());
		return new ModelAndView("/Admin/Tenant/TenantList", model);

	}

	@RequestMapping(value = "/tenantEdit")
	public ModelAndView tenantEdit(@RequestParam(value = "id") Long id) {
		Map<String, Object> model = prepareTenantModel();
		model.put("url", "/Admin/Tenant/tenantOverwrite.html");
		Tenant tenant = tenantService.getTenantById(id);
		ModelAndView mvc = new ModelAndView("/Admin/Tenant/TenantRegister", "model", model);
		mvc.addObject("tenant", tenant);
		return mvc;
	}

	@RequestMapping(value = "/tenantOverwrite", method = RequestMethod.POST)
	public ModelAndView updateTenant(@ModelAttribute("tenant") Tenant tenant, BindingResult result) {

		TenantValidator.validateTenant(tenant, result);
		validator.validate(tenant, result);
		if (result.hasErrors()) {
			Map<String, Object> model = prepareTenantModel();
			return new ModelAndView("/Admin/Tenant/TenantRegister", "model", model);
		}

		try {
			tenantService.updateTenant(tenant);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			result.rejectValue("email", "error.tenant", "Podany e-mail już istnieje");
			Map<String, Object> model = prepareTenantModel();
			return new ModelAndView("/Admin/Tenant/TenantRegister", "model", model);
		}
		return new ModelAndView("redirect:/Admin/Tenant/tenantList.html");
	}

	@RequestMapping(value = "/tenantDelete")
	public ModelAndView deleteTenant(@RequestParam(value = "id") Long id) {
		tenantService.deleteTenant(id);
		return new ModelAndView("redirect:/Admin/Tenant/tenantList.html");
	}

	@RequestMapping("/tenantCurrent")
	public ModelAndView currentLTenants() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("tenant", tenantService.getCurrentTenants());
		return new ModelAndView("/Admin/Tenant/TenantList", model);
	}

	public void setUserService(ApartmentService apartmentService) {
		this.apartmentService = apartmentService;

	}

	private Map<String, Object> prepareTenantModel() {
		List<Apartment> apartment = (apartmentService.getList());
		for (int i = 0; i < apartment.size(); i++) {
			if (apartment.get(0).getApartmentNumber() == 0) {
				apartment.remove(i);
				break;
			}
		}
		Map<String, Object> output = new HashMap<String, Object>();
		if (apartment.isEmpty()) {
			output.put("error", "Wprowadź przynajmniej jedno mieszkanie do bazy danych");
		}
		output.put("apartment", apartment);
		ArrayList<String> role = new ArrayList<>();
		role.add(UserRole.ADMIN.getUserRole());
		role.add(UserRole.USER.getUserRole());
		ArrayList<String> status = new ArrayList<>();
		status.add(UserStatus.ACTIVE.getUserStatus());
		status.add(UserStatus.INACTIVE.getUserStatus());
		output.put("role", role);
		output.put("status", status);
		Date date = new Date();
		output.put("movementDate", date);

		return output;
	}
}
