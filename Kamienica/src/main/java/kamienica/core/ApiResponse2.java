package kamienica.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ApiResponse2 {

	private Map<String, String> fieldErrors = new HashMap<>();

	public ApiResponse2() {

	}

	public void addErrorMessage(String element, String msg) {
		this.fieldErrors.put(element, msg);
	}

	public ApiResponse2(BindingResult result) {
		List<FieldError> err = result.getFieldErrors();
		for (FieldError fieldError : err) {
			this.fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
	}

	@Override
	public String toString() {
		return "ApiResonse2 [fieldErrors=" + fieldErrors + "]";
	}
	
	
}
