package kamienica.controller.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kamienica.core.Media;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.payment.PaymentService;
import kamienica.feature.reading.ReadingAbstract;
import kamienica.feature.tenant.Tenant;
import kamienica.feature.user_admin.AdminUserService;
import kamienica.feature.user_admin.MyUserDetailsService;
import kamienica.feature.user_admin.SecurityUser;

@RestController
@RequestMapping("/api/v1/home")
public class AdminUserRestController {

	@Autowired
	private PaymentService paymentService;
	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired
	private AdminUserService adminUserService;

	// =====================USER===========================================
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<SecurityUser> userHome() {

		SecurityUser myUser = userDetailsService.getCurrentUser();
		if (myUser != null) {
			return new ResponseEntity<SecurityUser>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SecurityUser>(myUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{media}/readings", method = RequestMethod.GET)
	public ResponseEntity<List<? extends ReadingAbstract>> getReadings(@PathVariable(value = "media") String media) {
		List<? extends ReadingAbstract> readings = null;
		Apartment ap = userDetailsService.getCurrentUser().getApartment();
		switch (media) {
		case "energy":

			readings = adminUserService.getReadingsForTenant(ap, Media.ENERGY);
			break;
		case "gas":

			readings = adminUserService.getReadingsForTenant(ap, Media.GAS);
			break;
		case "water":

			readings = adminUserService.getReadingsForTenant(ap, Media.WATER);
			break;
		default:
			break;
		}

		if (readings.isEmpty()) {
			return new ResponseEntity<List<? extends ReadingAbstract>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<? extends ReadingAbstract>>(readings, HttpStatus.OK);
	}

	@RequestMapping("/User/userPayment")
	public ModelAndView userPayment() {
		Map<String, Object> model = new HashMap<String, Object>();
		Tenant tenant = userDetailsService.getCurrentUser().getTenant();

		model.put("energy", paymentService.getPaymentEnergyForTenant(tenant));
		model.put("water", paymentService.getPaymentWaterForTenant(tenant));
		model.put("gas", paymentService.getPaymentGasForTenant(tenant));

		return new ModelAndView("/User/UserPayment", "model", model);
	}

	@RequestMapping("/User/userPassword")
	public ModelAndView changePassword() {
		return new ModelAndView("/User/UserPassword");
	}

	@RequestMapping("/User/userUpdatePassword")
	public ModelAndView updatePassword(@RequestParam String email, @RequestParam String oldPassword,
			@RequestParam String newPassword, @RequestParam String newPassword2) {
		HashMap<String, Object> model = new HashMap<>();
		if (!newPassword.equals(newPassword2) || newPassword == "" || newPassword2 == "") {
			model.put("error", "Wpisz poprawnie nowe hasło");
			return new ModelAndView("/User/UserPassword", "model", model);
		}

		try {
			userDetailsService.changePassword(email, oldPassword, newPassword);
		} catch (UsernameNotFoundException e) {
			model.put("error", "Niepoprawny login lub hasło");
			return new ModelAndView("/User/UserPassword", "model", model);
		}
		model.put("class", "alert-success");
		model.put("msg", "Hasło zostało zmienione");
		model.put("user", userDetailsService.getCurrentUser().getTenant());
		return new ModelAndView("/User/UserHome", "model", model);
	}
}
