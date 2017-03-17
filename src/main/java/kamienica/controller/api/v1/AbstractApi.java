package kamienica.controller.api.v1;

import kamienica.core.message.ApiErrorResponse;
import kamienica.feature.owner.IOwnerUserDataService;
import kamienica.model.entity.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class AbstractApi {

    @Autowired
    protected IOwnerUserDataService ownerUserDataService;

    protected static final String DUPLICATE_VALUE = "Duplicate";
    protected static final String CONSTRAINT_VIOLATION = "ConstraintViolation";
    protected static final String UNEXPECTED_ERROR = "UnexpectedError.";

    protected final ResponseEntity<ApiErrorResponse> sendErrorToUser(BindingResult result) {
        ApiErrorResponse message = new ApiErrorResponse();
        message.setErrors(result.getFieldErrors());
        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
