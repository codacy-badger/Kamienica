package kamienica.controller.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kamienica.conventer.ApartmentIB;
import kamienica.conventer.TenantIB;
import kamienica.core.DivisionManager;
import kamienica.forms.DivisionForm;
import kamienica.model.Apartment;
import kamienica.model.Division;
import kamienica.model.Tenant;
import kamienica.service.ApartmentService;
import kamienica.service.DivisionService;
import kamienica.service.TenantService;
import kamienica.validator.DivisionValidator;

@Controller
public class DivisionController {

	@Autowired
	private ApartmentService apartmentService;
	@Autowired
	private TenantService tenantService;
	@Autowired
	private DivisionService divisionService;

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Apartment.class, new ApartmentIB(this.apartmentService));
		binder.registerCustomEditor(Tenant.class, new TenantIB(this.tenantService));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@RequestMapping("/Admin/Division/divisionRegister")
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

	@RequestMapping(value = "/Admin/Division/divisionSave", method = RequestMethod.POST)
	public ModelAndView divisionSave(@ModelAttribute("divisionForm") DivisionForm divisionForm, BindingResult result,
			HttpServletRequest req) throws ParseException {
		Date date = divisionForm.getDate();
		List<Division> divisionList = divisionForm.getDivisionList();
		for (int i = 0; i < divisionList.size(); i++) {
			divisionList.get(i).setDate(date);
		}
		List<Apartment> apartmentList = apartmentService.getList();

		if (DivisionValidator.checksumForDivision(apartmentList, divisionList)) {
			divisionService.saveList(divisionList);
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

	@RequestMapping("/Admin/Division/divisionList")
	public ModelAndView divisionList() {
		Map<String, Object> model = new HashMap<String, Object>();
		DivisionForm divisionForm = new DivisionForm();
		divisionForm.setDivisionList((ArrayList<Division>) divisionService.getList());
		List<Tenant> tenants = tenantService.getCurrentTenants();
		List<Apartment> apartments = apartmentService.getList();
		if (!DivisionValidator.validateDivisionForPaymentController(apartments, divisionForm.getDivisionList(),
				tenants)) {
			model.put("error", "Podział jest nieaktualny. <a href='divisionRegister.html' class='alert alert-danger'><b>Zaktualizuj dane.</b></a> ");
		}
		model.put("tenantList", tenants);
		model.put("apartment", apartments);
		model.put("divisionForm", divisionForm);
		return new ModelAndView("/Admin/Division/DivisionList", "model", model);

	}
}
