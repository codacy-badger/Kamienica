package kamienica.controller.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.engine.transaction.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
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
import kamienica.model.Tenant;
import kamienica.model.UserRole;
import kamienica.model.UserStatus;
import kamienica.service.ApartmentService;
import kamienica.service.TenantService;
import kamienica.validator.TenantValidator;

@Controller
public class TenantController {

	@Autowired
	private ApartmentService apartmentService;
	@Autowired
	private TenantService tenantService;

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Apartment.class, new ApartmentIB(this.apartmentService));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@Autowired
	private Validator validator;


	public void setValidator(Validator validator) {
		this.validator = validator;
	}


	@RequestMapping("/Admin/Tenant/tenantRegister")
	public ModelAndView tenantRegister(@ModelAttribute("tenant") Tenant tenant, BindingResult result) {
		Map<String, Object> model = prepareTenantModel();
		return new ModelAndView("/Admin/Tenant/TenantRegister", "model", model);
	}

	@RequestMapping("/Admin/Tenant/tenantSave")
	public ModelAndView tenantSave(@Valid @ModelAttribute("tenant") Tenant tenant, BindingResult result) {
		TenantValidator.validateTenant(tenant, result);

//		validator.validate(tenant, result);
		if (result.hasErrors()) {
			Map<String, Object> model = prepareTenantModel();
			return new ModelAndView("/Admin/Tenant/TenantRegister", "model", model);
		}

		tenantService.saveTenant(tenant);
		return new ModelAndView("redirect:/Admin/Tenant/tenantList.html");
	}

	@RequestMapping("/Admin/Tenant/tenantList")
	public ModelAndView tenantList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("tenant", tenantService.getList());
		return new ModelAndView("/Admin/Tenant/TenantList", model);

	}

	@RequestMapping(value = "/Admin/Tenant/tenantEdit", params = { "id" })
	public ModelAndView tenantEdit(@RequestParam(value = "id") int id ) {
		Map<String, Object> model = prepareTenantModel();
	Tenant	tenant  = tenantService.getTenantById(id);
//	System.out.println(tenant.toString());
//		Tenant toEdit = tenantService.getTenantById(id);
//		tenant.setMovementDate(toEdit.getMovementDate());
//		tenant.setEmail(toEdit.getEmail());
//		tenant.setId(id);
//		tenant.setRole(toEdit.getRole());
//		tenant.setFirstName(toEdit.getFirstName());
//		tenant.setApartment(toEdit.getApartment());
//		tenant.setLastName(toEdit.getLastName());
//		tenant.setPhone(toEdit.getPhone());
//		model.put("tenant", tenant);
		ModelAndView mvc =  new ModelAndView("/Admin/Tenant/TenantEdit", "model", model);
		mvc.addObject("tenant",  tenant );
		return  mvc;
	}

	@RequestMapping("/Admin/Tenant/tenantOverwrite")
	public ModelAndView updateTenant(@ModelAttribute("tenant") Tenant tenant, BindingResult result) {

		TenantValidator.validateTenant(tenant, result);
		validator.validate(tenant, result);
		if (result.hasErrors()) {
			Map<String, Object> model = prepareTenantModel();
			return new ModelAndView("/Admin/Tenant/TenantEdit", "model", model);
		}

		try {
			tenantService.updateTenant(tenant);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			result.rejectValue("email", "error.tenant", "Podany e-mail już istnieje");
			Map<String, Object> model = prepareTenantModel();
			return new ModelAndView("/Admin/Tenant/TenantEdit", "model", model);
		}
		return new ModelAndView("redirect:/Admin/Tenant/tenantList.html");
	}

	@RequestMapping(value = "/Admin/Tenant/tenantDelete", params = { "id" })
	public ModelAndView usunTenant(@RequestParam(value = "id") int id) {
		tenantService.deleteTenant(id);
		return new ModelAndView("redirect:/Admin/Tenant/tenantList.html");
	}

	@RequestMapping("/Admin/Tenant/tenantCurrent")
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
