package kamienica.controller.api.v1;

import kamienica.core.util.SecurityDetails;
import kamienica.feature.payment.IPaymentService;
import kamienica.feature.user_admin.IOwnerUserDataService;
import kamienica.feature.user_admin.SecurityServiceImpl;
import kamienica.model.entity.*;
import kamienica.model.enums.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
public class AdminUserRestController {

	@Autowired
	private IPaymentService IPaymentService;
	@Autowired
	private SecurityServiceImpl userDetailsService;
	@Autowired
	private IOwnerUserDataService ownerUserDataService;

	// =====================USER===========================================
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<SecurityUser> userHome() {
		SecurityUser myUser = userDetailsService.getCurrentUser();
		if (myUser == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(myUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{media}/readings", method = RequestMethod.GET)
	public ResponseEntity<List<? extends Reading>> getReadings(@PathVariable(value = "media") String media) {
	
		List<? extends Reading> readings = null;
		Apartment ap = SecurityDetails.getLoggedTenant().getApartment();
		
		switch (media) {

		case "energy":
			readings = ownerUserDataService.getReadingsForTenant(ap, Media.ENERGY);
			break;

		case "gas":
			readings = ownerUserDataService.getReadingsForTenant(ap, Media.GAS);
			break;

		case "water":
			readings = ownerUserDataService.getReadingsForTenant(ap, Media.WATER);
			break;
		default:
			break;
		}

		if (readings.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(readings, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{media}/payments", method = RequestMethod.GET)
	public ResponseEntity<List<? extends Payment>> userPayment(@PathVariable(value = "media") Media media) {
		Tenant tenant = SecurityDetails.getLoggedTenant();
		List<? extends Payment> list;
		list = IPaymentService.getPaymentForTenant(tenant, media);

		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}


}
