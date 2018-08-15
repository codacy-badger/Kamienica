package kamienica.controller.api.v1;

import kamienica.core.message.Message;
import kamienica.core.util.SecurityDetails;
import kamienica.feature.apartment.IApartmentService;
import kamienica.model.entity.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kamienica.controller.api.v1.AbstractApi.CONSTRAINT_VIOLATION;

@RestController
@RequestMapping("/api/v1/apartments")
public class ApartmentApi {

    private final IApartmentService apartmentService;

    @Autowired
    public ApartmentApi(IApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> list(@RequestParam(required = false, value = "residence") final Long residenceId,
        @RequestParam(required = false, value = "showSharedPart", defaultValue = "false") final boolean showSharedPart) {
        final List<Apartment> list;
        if (residenceId == null) {
            list = apartmentService.list();
        } else {
            list = apartmentService.getByResidence(residenceId, showSharedPart);
        }

        if (list.isEmpty()) {
            return new ResponseEntity<List<Apartment>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody final Apartment apartment) {
        apartmentService.save(apartment);
        return new ResponseEntity<>(apartment, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateApartment(@RequestBody final Apartment apartment) {
        SecurityDetails.checkIfOwnsResidence(apartment.getResidence());
        apartmentService.update(apartment);
        return new ResponseEntity<>(apartment, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Message> delete(@PathVariable("id") final Long id) {
        final Message message = new Message("OK", null);
        try {
            final Apartment a = apartmentService.getById(id);
            SecurityDetails.checkIfOwnsResidence(a.getResidence());
            apartmentService.delete(a);
        } catch (Exception e) {
            message.setMessage(CONSTRAINT_VIOLATION);
            message.setException(e.toString());
            return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
