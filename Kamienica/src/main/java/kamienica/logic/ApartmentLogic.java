package kamienica.logic;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import kamienica.initBinder.ApartmentIB;
import kamienica.model.Apartment;
import kamienica.service.ApartmentService;

public class ApartmentLogic  {

	@Autowired
	ApartmentService apartmentService;
	@Autowired
	private Validator validator;

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Apartment.class, new ApartmentIB(this.apartmentService));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	
//	public  Map<String, Object> prepareTenantModel() {
//		List<Apartment> apartment = (apartmentService.getList());
//		Map<String, Object> output = new HashMap<String, Object>();
//		if (apartment.isEmpty()) {
//			output.put("error", "Wprowadź przynajmniej jedno mieszkanie do bazy danych");
//		}
//		output.put("apartment", apartment);
//		ArrayList<String> role = new ArrayList<>();
//		role.add(UserRole.ADMIN.getUserRole());
//		role.add(UserRole.USER.getUserRole());
//		output.put("role", role);
//		Date date = new Date();
//		output.put("movementDate", date);
//
//		return output;
//	}
//	
	@Valid
	public void apartmentSave(BindingResult result, String url, Apartment apartment) {
		System.out.println(apartment.toString());
		validator.validate(apartment, result);
		if (result.hasErrors()) {
			url = "/Admin/Apartment/ApartmentRegister";
		}

		try {
			apartmentService.save(apartment);
		} catch (ConstraintViolationException e) {
			result.rejectValue("apartmentNumber", "error.apartment", "Istnieje już taki numer mieszkania w bazie");
			url = "/Admin/Apartment/ApartmentRegister";
		}
	}
}
