package kamienica.controller.json;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentService;

@RestController
@RequestMapping("/api/v1/apartments")
public class ApartmentRestController {

	@Autowired
	ApartmentService apartmentService;

	// --------------multiple_apartments----
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Apartment>> listAllApartments() {
		System.out.println("pobieranie listy mieszkań....");
		List<Apartment> list = apartmentService.getList();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Apartment>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Apartment>>(list, HttpStatus.OK);
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
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Void> createApartment(@Valid @RequestBody Apartment apartment 
			//,UriComponentsBuilder ucBuilder
			,BindingResult result
			
			) {
//		if (apartmentService.getById(apartment.getId()) != null) {
//			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//		}
		
		System.out.println("=========================zapis===================");
		if(result.hasErrors()) {
			System.out.println("zle!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
		apartmentService.save(apartment);

//		HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(ucBuilder.path("/apartments/{id}").buildAndExpand(apartment.getId()).toUri());
//		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		return new ResponseEntity<Void>( HttpStatus.CREATED);
	}

	// update
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Apartment> updateUser(@PathVariable("id") Long id, @RequestBody Apartment apartment) {

		Apartment currentApartment = apartmentService.getById(id);

		if (currentApartment == null) {
			return new ResponseEntity<Apartment>(HttpStatus.NOT_FOUND);
		}

		currentApartment.setApartmentNumber(apartment.getApartmentNumber());
		currentApartment.setDescription(apartment.getDescription());
		currentApartment.setIntercom(apartment.getIntercom());

		apartmentService.update(currentApartment);
		return new ResponseEntity<Apartment>(currentApartment, HttpStatus.OK);
	}

	// delete by id
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Apartment> deleteUser(@PathVariable("id") Long id) {
		Apartment apartment = apartmentService.getById(id);
		if (apartment == null) {

			return new ResponseEntity<Apartment>(HttpStatus.NOT_FOUND);
		}

		apartmentService.deleteByID(id);
		return new ResponseEntity<Apartment>(HttpStatus.NO_CONTENT);
	}
}
