package kamienica.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import kamienica.core.ApiResponse;
import kamienica.core.ApiResponse2;
import kamienica.core.Message;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentService;

@RestController
@RequestMapping("/api/v1/apartments")
public class ApartmentRestController {

	@Autowired
	ApartmentService apartmentService;

	// --------------multiple_apartments----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> listAllApartments() {
		System.out.println("pobieranie listy mieszkań....");
		List<Apartment> list = apartmentService.getList();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Apartment>>(HttpStatus.NOT_FOUND);
		}
		ApiResponse2<Apartment> response = new ApiResponse2<>();
		response.setObjectList(list);
		return new ResponseEntity<ApiResponse2<Apartment>>(response, HttpStatus.OK);
	}

	// --------------single_apartment----
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Apartment> getById(@PathVariable Long id) {
		Apartment apartment = apartmentService.getById(id);
		if (apartment == null) {
			return new ResponseEntity<Apartment>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Apartment>(apartment, HttpStatus.OK);
	}

	// create new
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createApartment(@Valid @RequestBody Apartment apartment
			, BindingResult result) {
		// if (apartmentService.getById(apartment.getId()) != null) {
		// return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		// }

		System.out.println("=========================zapis===================");
		System.out.println(result.getAllErrors().toString());
		if (result.hasErrors()) {
			ApiResponse message = new ApiResponse();
			message.setErrors(result.getFieldErrors());
			System.out.println(result.getFieldErrors());
			// ApiError err = new ApiError();
			// for (result iterable_element : iterable) {
			//
			// }
			System.out.println("zle!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return new ResponseEntity<ApiResponse>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			apartmentService.save(apartment);
		} catch (Exception e) {
			result.rejectValue("apartmentNumber", "error.apartment", "Istniej już taki numer w bazie");
			ApiResponse message = new ApiResponse();
			message.addErrorMessage("apartmentNumber", "Istniej już taki numer w bazie");
			message.setErrors(result.getFieldErrors());
			System.out.println(result.getFieldErrors());
			Map<String, String> test = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				test.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			test.put("test", "wartoscTestu");
			return new ResponseEntity<Map<String, String>>(test, HttpStatus.CONFLICT);
		}

		// HttpHeaders headers = new HttpHeaders();
		// headers.setLocation(ucBuilder.path("/apartments/{id}").buildAndExpand(apartment.getId()).toUri());
		// return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		return new ResponseEntity<Apartment>(apartment, HttpStatus.CREATED);
	}

	// update
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Apartment> updateUser(@PathVariable("id") Long id, @RequestBody Apartment apartment) {

		// Apartment currentApartment = apartmentService.getById(id);

		// if (currentApartment == null) {
		// return new ResponseEntity<Apartment>(HttpStatus.NOT_FOUND);
		// }

		// currentApartment.setApartmentNumber(apartment.getApartmentNumber());
		// currentApartment.setDescription(apartment.getDescription());
		// currentApartment.setIntercom(apartment.getIntercom());

		apartmentService.update(apartment);
		return new ResponseEntity<Apartment>(apartment, HttpStatus.OK);
	}

	// delete by id
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Message> deleteUser(@PathVariable("id") Long id) {
		Message message = new Message("OK", null);
		try {

			apartmentService.deleteByID(id);
		} catch (Exception e) {
			message.setMessage("Nie można usunać mieszkania, dla których istnieją powiązania w bazie");
			message.setException(e.toString());
			return new ResponseEntity<Message>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
}
