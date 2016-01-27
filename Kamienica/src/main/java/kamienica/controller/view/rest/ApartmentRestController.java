package kamienica.controller.view.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import kamienica.model.Apartment;
import kamienica.service.ApartmentService;

@RestController
@RequestMapping("/Rest")
public class ApartmentRestController  {

	@Autowired
	ApartmentService apartmentService;

	// @RequestMapping("/apartment/{id}")
	// public String getRestApartment(@PathVariable int id, Model model) {
	// @SuppressWarnings("unchecked")
	// List<Apartment> aparment = (List<Apartment>)
	// apartmentService.getById(id);
	// model.addAttribute("aparment", aparment);
	// return "/Rest/apartment";
	// }

	// --------------multiple_apartments----
	@RequestMapping(value = "/apartments", method = RequestMethod.GET)
	public List<Apartment> listAllApartments() {
		return apartmentService.getList();
	}

	// --------------single_apartment----
	@RequestMapping(value = "/apartments/{id}", method = RequestMethod.GET)
	public ResponseEntity<Apartment> getRestApartment(@PathVariable int id) {
		Apartment apartment = apartmentService.getById(id);
		if (apartment == null) {
			return new ResponseEntity<Apartment>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Apartment>(apartment, HttpStatus.OK);
	}
	
//	@RequestMapping(value = "/apartments", params= {"id"}, method = RequestMethod.GET)
//	public ResponseEntity<Apartment> getRestApartment2(@PathVariable int id) {
//		Apartment apartment = apartmentService.getById(id);
//		if (apartment == null) {
//			return new ResponseEntity<Apartment>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<Apartment>(apartment, HttpStatus.OK);
//	}
//	

	// create new
	@RequestMapping(value = "/apartments", method = RequestMethod.POST)
	public ResponseEntity<Void> createApartment(@RequestBody Apartment apartment, UriComponentsBuilder ucBuilder) {
		if (apartmentService.getById(apartment.getId()) != null) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		apartmentService.save(apartment);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/apartments/{id}").buildAndExpand(apartment.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// update
	@RequestMapping(value = "/apartments/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Apartment> updateUser(@PathVariable("id") int id, @RequestBody Apartment apartment) {
System.out.println("start resta-------------------");
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
	 @RequestMapping(value = "/apartments/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Apartment> deleteUser(@PathVariable("id") int id) {
	        
		 Apartment apartment = apartmentService.getById(id);
	        if (apartment == null) {
	          
	            return new ResponseEntity<Apartment>(HttpStatus.NOT_FOUND);
	        }
	 
	        apartmentService.deleteByID(id);
	        return new ResponseEntity<Apartment>(HttpStatus.NO_CONTENT);
	    }
}
