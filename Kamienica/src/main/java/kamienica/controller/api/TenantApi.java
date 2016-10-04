package kamienica.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
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

import kamienica.core.util.ApiResponse;
import kamienica.core.util.ApiResponse2;
import kamienica.core.util.Message;
import kamienica.feature.apartment.ApartmentService;
import kamienica.feature.tenant.Tenant;
import kamienica.feature.tenant.TenantService;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantApi extends AbstractApi {

	@Autowired
	TenantService service;
	@Autowired
	ApartmentService apService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getList() {
		List<Tenant> list = service.getList();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Tenant>>(HttpStatus.NOT_FOUND);
		}
		ApiResponse2<Tenant> response = new ApiResponse2<>();
		response.setObjectList(list);
		return new ResponseEntity<List<Tenant>>(list, HttpStatus.OK);
	}

//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<Tenant> getById(@PathVariable Long id) {
//		Tenant tenant = service.getTenantById(id);
//		if (tenant == null) {
//			return new ResponseEntity<Tenant>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<Tenant>(tenant, HttpStatus.OK);
//	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@Valid @RequestBody Tenant tenant, BindingResult result) {
		if (result.hasErrors()) {
			ApiResponse message = new ApiResponse();
			message.setErrors(result.getFieldErrors());
			return new ResponseEntity<ApiResponse>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		try {
			service.saveTenant(tenant);
		} catch (ConstraintViolationException e) {
			result.rejectValue("apartmentNumber", "error.apartment", DUPLICATE_VALUE);

			Map<String, String> test = new HashMap<>();
			for (FieldError fieldError : result.getFieldErrors()) {
				test.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(test, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Tenant>(tenant, HttpStatus.CREATED);
	}

	// delete by id
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Message> deleteUser(@PathVariable("id") Long id) {
		Message message = new Message("OK", null);
		try {
			service.deleteTenant(id);
		} catch (Exception e) {
			message.setMessage(CONSTRAINT_VIOLATION);
			message.setException(e.toString());
			return new ResponseEntity<Message>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	// update
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@Valid @PathVariable("id") Long id, @RequestBody Tenant tenant,
			BindingResult result) {

		if (result.hasErrors()) {
			ApiResponse message = new ApiResponse();
			message.setErrors(result.getFieldErrors());
			return new ResponseEntity<ApiResponse>(message, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		service.updateTenant(tenant);
		return new ResponseEntity<Tenant>(tenant, HttpStatus.OK);
	}
}
