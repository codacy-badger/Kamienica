package kamienica.feature.meter;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ValidatorMeter implements Validator {

	public ValidatorMeter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

	}

}
