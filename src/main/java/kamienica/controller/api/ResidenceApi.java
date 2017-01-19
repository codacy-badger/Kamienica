package kamienica.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import kamienica.feature.user_admin.OwnerUserDataService;
import kamienica.feature.user_admin.SecurityService;
import kamienica.model.Tenant;
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

import kamienica.controller.ControllerMessages;
import kamienica.core.message.ApiErrorResponse;
import kamienica.core.message.Message;
import kamienica.feature.residence.ResidenceService;
import kamienica.model.Residence;

@RestController
@RequestMapping("/api/v1/residences")
public class ResidenceApi {

    @Autowired
    private ResidenceService residenceService;
    @Autowired
    private OwnerUserDataService ownerUserDataService;

    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> list() {
        final Tenant t = ownerUserDataService.getCurrentTenant();

        final List<Residence> list = residenceService.listForOwner(t);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid @RequestBody final Residence residence, final BindingResult result) {

        if (result.hasErrors()) {
            final ApiErrorResponse message = new ApiErrorResponse();
            message.setErrors(result.getFieldErrors());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            final Tenant t = ownerUserDataService.getCurrentTenant();
            residenceService.save(residence, t);
        } catch (Exception e) {
            result.rejectValue("residenceNumber", "error.residence", ControllerMessages.DUPLICATE_VALUE);
            final Map<String, String> test = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                test.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(test, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(residence, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("id") final Long id, 
                                    @RequestBody final Residence residence, final BindingResult result) {

        if (result.hasErrors()) {
            ApiErrorResponse message = new ApiErrorResponse();
            message.setErrors(result.getFieldErrors());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            residenceService.update(residence);
        } catch (Exception e) {
            result.rejectValue("residenceNumber", "error.residence", ControllerMessages.UNEXPECTED_ERROR);
            final Map<String, String> test = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                test.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(test, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(residence, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Message> delete(@PathVariable("id") final Long id) {
        final Message message = new Message("OK", null);
        try {
            residenceService.deleteById(id);
        } catch (Exception e) {
            message.setMessage(ControllerMessages.CONSTRAINT_VIOLATION);
            message.setException(e.toString());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
