package kamienica.controller.api.v1;

import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class AbstractApi {

    static final String DUPLICATE_VALUE = "Duplicate";
    static final String CONSTRAINT_VIOLATION = "ConstraintViolation";

}
