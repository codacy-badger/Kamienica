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
import kamienica.feature.apartment.Apartment;
import kamienica.feature.apartment.ApartmentService;
import kamienica.feature.tenant.Tenant;
import kamienica.feature.tenant.TenantService;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantRestController {

	@Autowired
	TenantService service;
	@Autowired
	ApartmentService apService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getList() {
		List<Tenant> list = service.getList();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Tenant>>(HttpStatus.NOT_FOUND);
		}
		ApiResponse2<Tenant> response = new ApiResponse2<>();
		response.setObjectList(list);
		return new ResponseEntity<List<Tenant>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<?> hello() {
		
		return new ResponseEntity<String>("hello", HttpStatus.OK);
	}

	@RequestMapping(value = "/old", method = RequestMethod.GET)
	public ResponseEntity<?> test() {
		List<Tenant> list = service.getList();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Tenant>>(HttpStatus.NOT_FOUND);
		}
		ApiResponse2<Tenant> response = new ApiResponse2<>();
		response.setObjectList(list);
		response.setNestedElements(apService.getList());
		return new ResponseEntity<ApiResponse2<Tenant>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Tenant> getById(@PathVariable Long id) {
		Tenant tenant = service.getTenantById(id);
		if (tenant == null) {
			return new ResponseEntity<Tenant>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Tenant>(tenant, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@Valid @RequestBody Tenant tenant, BindingResult result) {
		System.out.println("=============================");
		System.out.println(tenant);

		// if (result.hasErrors()) {
		// ApiResponse message = new ApiResponse();
		// message.setErrors(result.getFieldErrors());
		//
		// return new ResponseEntity<ApiResponse>(message,
		// HttpStatus.UNPROCESSABLE_ENTITY);
		// }
		// try {
		//
		// service.saveTenant(tenant);
		// } catch (Exception e) {
		// result.rejectValue("email", "error.email", "Istniej już taki email w
		// bazie");
		// ApiResponse message = new ApiResponse();
		// message.addErrorMessage("apartmentNumber", "Istniej już taki email w
		// bazie");
		// message.setErrors(result.getFieldErrors());
		// System.out.println(result.getFieldErrors());
		// Map<String, String> test = new HashMap<>();
		// for (FieldError fieldError : result.getFieldErrors()) {
		// test.put(fieldError.getField(), fieldError.getDefaultMessage());
		// }
		// test.put("test", "wartoscTestu");
		// return new ResponseEntity<Map<String, String>>(test,
		// HttpStatus.CONFLICT);
		// }
		return new ResponseEntity<ApiResponse>(new ApiResponse(), HttpStatus.CREATED);
	}
}
