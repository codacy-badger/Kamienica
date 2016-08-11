package kamienica.controller.json;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kamienica.feature.tenant.Tenant;
import kamienica.feature.tenant.TenantService;

@RestController
@RequestMapping("/api/v1/tenants")
public class TenantRestController {

	@Autowired
	TenantService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Tenant>> getList() {
		List<Tenant> list = service.getList();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Tenant>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Tenant>>(list, HttpStatus.OK);
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
