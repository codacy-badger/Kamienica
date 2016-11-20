package kamienica.controller.api;

import kamienica.core.util.ApiResponse;
import kamienica.core.util.Message;
import kamienica.feature.apartment.Apartment;
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
public class ApartmentApi extends AbstractApi{

	@Autowired
	ApartmentService apartmentService;

	// --------------multiple_apartments----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> listAllApartments() {
		List<Apartment> list = apartmentService.getList();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Apartment>>(HttpStatus.NOT_FOUND);
		}
//		ApiResponse2<Apartment> response = new ApiResponse2<>();
//		response.setObjectList(list);
		return new ResponseEntity<List<Apartment>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/paginated", params = { "page", "size" }, method = RequestMethod.GET)
	public ResponseEntity<?> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size,
			UriComponentsBuilder uriBuilder) {

		List<Apartment> list = apartmentService.paginatedList(page, size);

		HttpHeaders headers = new HttpHeaders();
		headers.set("page", String.valueOf(page));
		headers.set("maxResult", String.valueOf(size));
		return new ResponseEntity<List<Apartment>>(list, headers, HttpStatus.OK);
	}

	// --------------single_apartment----
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<Apartment> getById(@PathVariable Long id) {
//		Apartment apartment = apartmentService.getById(id);
//		if (apartment == null) {
//			return new ResponseEntity<Apartment>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<Apartment>(apartment, HttpStatus.OK);
//	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createApartment(@Valid @RequestBody Apartment apartment, BindingResult result) {

		if (result.hasErrors()) {
			ApiResponse message = new ApiResponse();
			message.setErrors(result.getFieldErrors());
			return new ResponseEntity<ApiResponse>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			apartmentService.save(apartment);
		} catch (Exception e) {
			result.rejectValue("apartmentNumber", "error.apartment", DUPLICATE_VALUE);
			Map<String, String> test = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				test.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(test, HttpStatus.CONFLICT);
		}

		// HttpHeaders headers = new HttpHeaders();
		// headers.s
		// headers.setLocation(ucBuilder.path("/apartments/{id}").buildAndExpand(apartment.getId()).toUri());
		// return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		return new ResponseEntity<Apartment>(apartment, HttpStatus.CREATED);
	}

	// update
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody Apartment apartment, BindingResult result) {

		if (result.hasErrors()) {
			ApiResponse message = new ApiResponse();
			message.setErrors(result.getFieldErrors());
			return new ResponseEntity<ApiResponse>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			apartmentService.update(apartment);
		} catch (Exception e) {
			result.rejectValue("apartmentNumber", "error.apartment", UNEXPECTED_ERROR);
			Map<String, String> test = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				test.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(test, HttpStatus.CONFLICT);
		}


        return new ResponseEntity<Apartment>(apartment, HttpStatus.OK);
	}

	// delete by id
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Message> deleteUser(@PathVariable("id") Long id) {
		Message message = new Message("OK", null);
		try {
			apartmentService.deleteByID(id);
		} catch (Exception e) {
			message.setMessage(CONSTRAINT_VIOLATION);
			message.setException(e.toString());
			return new ResponseEntity<Message>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
}
