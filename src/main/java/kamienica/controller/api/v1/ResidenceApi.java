package kamienica.controller.api.v1;

import kamienica.core.message.ApiErrorResponse;
import kamienica.core.message.Message;
import kamienica.core.util.SecurityDetails;
import kamienica.feature.residence.IPurgeService;
import kamienica.feature.residence.IResidenceService;
import kamienica.model.entity.Residence;
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

import static kamienica.controller.api.v1.AbstractApi.*;

@RestController
@RequestMapping("/api/v1/residences")
public class ResidenceApi {

    private final IResidenceService residenceService;
    private final IPurgeService purgeService;

    @Autowired
    public ResidenceApi(final IResidenceService residenceService, final IPurgeService purgeService) {
        this.residenceService = residenceService;
        this.purgeService = purgeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> list() {

        final List<Residence> list = residenceService.listForOwner();
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
            residenceService.save(residence);
        } catch (Exception e) {
            result.rejectValue("residenceNumber", "error.residence", DUPLICATE_VALUE);
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
            SecurityDetails.checkIfOwnsResidence(residence);
            residenceService.update(residence);
        } catch (Exception e) {
            final Map<String, String> errorList = new HashMap<>();
            errorList.put("general error", e.getMessage());
            for (FieldError fieldError : result.getFieldErrors()) {
                errorList.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errorList, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(residence, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Message> delete(@PathVariable("id") final Long id) {
        final Message message = new Message("OK", null);
        try {
            final Residence r = residenceService.getById(id);
            SecurityDetails.checkIfOwnsResidence(r);
            purgeService.purgeData(r);
        } catch (Exception e) {
            message.setMessage(CONSTRAINT_VIOLATION);
            message.setException(e.toString());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
