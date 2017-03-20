package kamienica.controller.api.v1;

import kamienica.core.message.ApiErrorResponse;
import kamienica.feature.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class AbstractApi {

    @Autowired
    protected IUserService ownerUserDataService;

    protected static final String DUPLICATE_VALUE = "Duplicate";
    protected static final String CONSTRAINT_VIOLATION = "ConstraintViolation";

}
