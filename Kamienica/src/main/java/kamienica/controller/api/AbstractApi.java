package kamienica.controller.api;

import kamienica.core.message.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class AbstractApi {

	protected static final String DUPLICATE_VALUE = "Istnieje już taka wartość w bazie danych";
	protected static final String CONSTRAINT_VIOLATION = "Nie można usunąć elementu, dla którego istnieją powiązania w baize";
	protected static final String UNEXPECTED_ERROR = "Wystąpił nieoczekiwany błąd podczas zapisu.";

	protected final ResponseEntity<ApiErrorResponse> sendErrorToUser(BindingResult result) {
		ApiErrorResponse message = new ApiErrorResponse();
		message.setErrors(result.getFieldErrors());
		return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
