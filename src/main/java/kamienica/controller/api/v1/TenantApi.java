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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantApi extends AbstractApi {

    private final ITenantService tenantService;

    @Autowired
    public TenantApi(ITenantService tenantService) {
        this.tenantService = tenantService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> list(@RequestParam(required = false, value = "residence") final Long residenceId) {
        final List<Tenant> list;
        if(residenceId == null) {
            list =  tenantService.listForOwner();
        } else {

            list = tenantService.findForSpecifiedResicence(residenceId);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/owners", method = RequestMethod.GET)
    public ResponseEntity<?> listOwners() {
        final List<Tenant> list = tenantService.getOwners();
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
            tenantService.save(tenant);
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
            final Tenant t = tenantService.getById(id);
            SecurityDetails.checkIfOwnsResidence(t.getRentContract().getApartment().getResidence());
            tenantService.delete(t);
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
        tenantService.update(tenant);
        return new ResponseEntity<>(tenant, HttpStatus.OK);
    }
}
