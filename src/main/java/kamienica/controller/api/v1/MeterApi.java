package kamienica.controller.api.v1;

import kamienica.core.message.ApiErrorResponse;
import kamienica.core.message.Message;
import kamienica.feature.meter.IMeterService;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Meter;
import kamienica.model.enums.Media;
import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/meters")
public class MeterApi extends AbstractApi {

    private final IMeterService service;

    @Autowired
    public MeterApi(IMeterService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{media}", method = RequestMethod.GET)
    public ResponseEntity<?> getList(@PathVariable Media media, @RequestParam(required = false) final LocalDate date) {
        final List<? extends Meter> list = service.getListForOwner(media);
        if (list.isEmpty()) {
            return new ResponseEntity<List<Apartment>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<? extends Meter>>(list, HttpStatus.OK);
    }


    @RequestMapping(value = "/{media}", method = RequestMethod.POST)
    public ResponseEntity<?> create(@PathVariable final Media media, @Valid @RequestBody final Meter meter, final BindingResult result) {
        if (result.hasErrors()) {
            final ApiErrorResponse message = new ApiErrorResponse();
            message.setErrors(result.getFieldErrors());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            service.save(meter);
        } catch (ConstraintViolationException e) {
            result.rejectValue("serialNumber", "error.serialNumber", DUPLICATE_VALUE);
            final Map<String, String> test = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                test.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(test, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(meter, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{media}/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable final Media media, @Valid @RequestBody final Meter meter, final BindingResult result) {
        if (result.hasErrors()) {
            final ApiErrorResponse message = new ApiErrorResponse();
            message.setErrors(result.getFieldErrors());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            service.update(meter);
        } catch (ConstraintViolationException e) {
            result.rejectValue("serialNumber", "error.serialNumber", DUPLICATE_VALUE);
            final Map<String, String> test = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                test.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(test, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(meter, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{media}/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Message> delete(@PathVariable("media") final Media media, @PathVariable("id") final Long id) {
        final Message message = new Message("OK", null);
        try {
            service.delete(id);
        } catch (Exception e) {
            message.setMessage(CONSTRAINT_VIOLATION);
            message.setException(e.toString());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
