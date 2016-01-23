package kamienica.validator;

import org.springframework.validation.BindingResult;

import kamienica.model.Tenant;

public class TenantValidator {

	public static void validateTenant(Tenant tenant, BindingResult result) {

		if (tenant.getApartment().getApartmentNumber() == 0) {
			result.rejectValue("apartment", "error.tenant", "Nie można wprowadzić do części wspólnej");
		}
		if (tenant.getPassword().length() != 5) {
			result.rejectValue("password", "error.tenant", "Hasło musz zawierać 5 znaków");
		}
		if (tenant.getPhone().equals("")) {
			tenant.setPhone(null);
		}

	}
}
