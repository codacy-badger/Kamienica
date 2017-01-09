package kamienica.controller.api;

import kamienica.core.enums.Media;
import kamienica.model.Payment;
import kamienica.feature.payment.PaymentService;
import kamienica.model.Reading;
import kamienica.feature.user_admin.OwnerUserDataService;
import kamienica.feature.user_admin.SecurityService;
import kamienica.model.SecurityUser;
import kamienica.model.Apartment;
import kamienica.model.Tenant;
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
	private PaymentService paymentService;
	@Autowired
	private SecurityService userDetailsService;
	@Autowired
	private OwnerUserDataService ownerUserDataService;

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
		Apartment ap = userDetailsService.getCurrentUser().getTenant().getApartment();
		
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
		Tenant tenant = userDetailsService.getCurrentUser().getTenant();
		List<? extends Payment> list;
		list = paymentService.getPaymentForTenant(tenant, media);

		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}


}
