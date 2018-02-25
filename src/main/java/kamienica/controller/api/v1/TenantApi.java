package kamienica.controller.api.v1;

import kamienica.core.message.ApiErrorResponse;
import kamienica.core.message.Message;
import kamienica.core.util.SecurityDetails;
import kamienica.feature.tenant.ITenantService;
import kamienica.model.entity.Tenant;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantApi extends AbstractApi {

    private final ITenantService service;

    @Autowired
    public TenantApi(ITenantService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> list() {
        final List<Tenant> list = service.listForOwner();

        if (list.isEmpty()) {
            return new ResponseEntity<List<Tenant>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/owners", method = RequestMethod.GET)
    public ResponseEntity<?> listOwners() {
        final List<Tenant> list = service.getOwners();
        if (list.isEmpty()) {
            return new ResponseEntity<List<Tenant>>(HttpStatus.NOT_FOUND);
        }
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
            SecurityDetails.checkIfOwnsResidence(tenant.getRentContract().getApartment().getResidence());
            service.save(tenant);
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
            final Tenant t = service.getById(id);
            SecurityDetails.checkIfOwnsResidence(t.getRentContract().getApartment().getResidence());
            service.delete(t);
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
        SecurityDetails.checkIfOwnsResidence(tenant.getRentContract().getApartment().getResidence());
        service.update(tenant);
        return new ResponseEntity<>(tenant, HttpStatus.OK);
    }
}
