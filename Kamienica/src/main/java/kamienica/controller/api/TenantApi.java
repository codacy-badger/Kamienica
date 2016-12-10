package kamienica.controller.api;

import kamienica.core.message.ApiErrorResponse;
import kamienica.core.message.ApiResponse;
import kamienica.core.message.Message;
import kamienica.model.Tenant;
import kamienica.feature.tenant.TenantService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantApi extends AbstractApi {

    @Autowired
    private TenantService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> list(@RequestParam(value = "deactivated", required = false) final boolean deactivated) {
        final List<Tenant> list;
        if (deactivated) {
            list = service.getList();
        } else {
            list = service.getActiveTenants();
        }

        if (list.isEmpty()) {
            return new ResponseEntity<List<Tenant>>(HttpStatus.NOT_FOUND);
        }
        final ApiResponse<Tenant> response = new ApiResponse<>();
        response.setObjectList(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@Valid @RequestBody final Tenant tenant, final BindingResult result) {
        if (result.hasErrors()) {
            final ApiErrorResponse message = new ApiErrorResponse();
            message.setErrors(result.getFieldErrors());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            service.saveTenant(tenant);
        } catch (ConstraintViolationException e) {
            result.rejectValue("apartmentNumber", "error.apartment", DUPLICATE_VALUE);

            Map<String, String> test = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                test.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(test, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(tenant, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Message> delete(@PathVariable("id") final Long id) {
        Message message = new Message("OK", null);
        try {
            service.deleteTenant(id);
        } catch (Exception e) {
            message.setMessage(CONSTRAINT_VIOLATION);
            message.setException(e.toString());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@Valid @PathVariable("id") final Long id, @RequestBody final Tenant tenant,
                                    BindingResult result) {

        if (result.hasErrors()) {
            ApiErrorResponse message = new ApiErrorResponse();
            message.setErrors(result.getFieldErrors());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        service.updateTenant(tenant);
        return new ResponseEntity<>(tenant, HttpStatus.OK);
    }
}
