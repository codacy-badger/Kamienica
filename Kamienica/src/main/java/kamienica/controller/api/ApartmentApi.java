package kamienica.controller.api;

import kamienica.core.message.ApiErrorResponse;
import kamienica.core.message.Message;
import kamienica.model.Apartment;
import kamienica.feature.apartment.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/apartments")
public class ApartmentApi extends AbstractApi {

    @Autowired
    private ApartmentService apartmentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> list() {
        final List<Apartment> list = apartmentService.getList();
        if (list.isEmpty()) {
            return new ResponseEntity<List<Apartment>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/paginated", params = {"page", "size"}, method = RequestMethod.GET)
    public ResponseEntity<?> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size,
                                           UriComponentsBuilder uriBuilder) {

        final List<Apartment> list = apartmentService.paginatedList(page, size);

        HttpHeaders headers = new HttpHeaders();
        headers.set("page", String.valueOf(page));
        headers.set("maxResult", String.valueOf(size));
        return new ResponseEntity<>(list, headers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@Valid @RequestBody final Apartment apartment, final BindingResult result) {

        if (result.hasErrors()) {
            final ApiErrorResponse message = new ApiErrorResponse();
            message.setErrors(result.getFieldErrors());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            apartmentService.save(apartment);
        } catch (Exception e) {
            result.rejectValue("apartmentNumber", "error.apartment", DUPLICATE_VALUE);
            final Map<String, String> test = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                test.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(test, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(apartment, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") final Long id, @RequestBody final Apartment apartment, final BindingResult result) {

        if (result.hasErrors()) {
            ApiErrorResponse message = new ApiErrorResponse();
            message.setErrors(result.getFieldErrors());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            apartmentService.update(apartment);
        } catch (Exception e) {
            result.rejectValue("apartmentNumber", "error.apartment", UNEXPECTED_ERROR);
            final Map<String, String> test = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                test.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(test, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(apartment, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Message> delete(@PathVariable("id") final Long id) {
        final Message message = new Message("OK", null);
        try {
            apartmentService.deleteByID(id);
        } catch (Exception e) {
            message.setMessage(CONSTRAINT_VIOLATION);
            message.setException(e.toString());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
