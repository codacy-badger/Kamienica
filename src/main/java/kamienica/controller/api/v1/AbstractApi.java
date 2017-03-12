package kamienica.controller.api.v1;

import kamienica.core.message.ApiErrorResponse;
import kamienica.core.util.SecurityDetails;
import kamienica.feature.user.IUserService;
import kamienica.model.entity.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class AbstractApi {

    @Autowired
    protected IUserService ownerUserDataService;

    protected static final String DUPLICATE_VALUE = "Istnieje już taka wartość w bazie danych";
    protected static final String CONSTRAINT_VIOLATION = "Nie można usunąć elementu, dla którego istnieją powiązania w bazie";
    protected static final String UNEXPECTED_ERROR = "Wystąpił nieoczekiwany błąd podczas zapisu.";

    protected final ResponseEntity<ApiErrorResponse> sendErrorToUser(BindingResult result) {
        ApiErrorResponse message = new ApiErrorResponse();
        message.setErrors(result.getFieldErrors());
        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    protected Tenant techLoggedTenant() {
        return SecurityDetails.getLoggedTenant();
    }
}
