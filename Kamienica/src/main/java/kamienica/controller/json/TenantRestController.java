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
@RequestMapping("/api/tenants")
public class TenantRestController {

	@Autowired
	TenantService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Tenant> getList() {

		return service.getList();

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
