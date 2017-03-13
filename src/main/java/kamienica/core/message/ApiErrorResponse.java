package kamienica.core.message;

import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiErrorResponse {

	private String message;
	private List<FieldError> errors;
	private Map<String, String> fieldErrors = new HashMap<>();

	public void addErrorMessage(String element, String msg) {
		this.fieldErrors.put(element, msg);
	}

	public String getMessage() {
		return message;
	}

	public Map<String, String> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(Map<String, String> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<FieldError> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldError> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "ApiErrorResponse [message=" + message + ", errors=" + errors + "]";
	}

}
