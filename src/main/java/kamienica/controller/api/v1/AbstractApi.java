package kamienica.controller.api.v1;

import kamienica.feature.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class AbstractApi {

    @Autowired
    protected IUserService ownerUserDataService;

    static final String DUPLICATE_VALUE = "Duplicate";
    static final String CONSTRAINT_VIOLATION = "ConstraintViolation";

}
