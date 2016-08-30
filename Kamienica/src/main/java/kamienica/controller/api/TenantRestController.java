package kamienica.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kamienica.core.ApiResponse2;
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

}
