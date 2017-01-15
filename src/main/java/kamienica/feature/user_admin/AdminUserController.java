package kamienica.feature.user_admin;

import kamienica.core.enums.Media;
import kamienica.feature.payment.PaymentService;
import kamienica.model.Apartment;
import kamienica.model.SecurityUser;
import kamienica.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class AdminUserController {

	@Autowired
	private PaymentService paymentService;
	@Autowired
	private SecurityService userDetailsService;
	@Autowired
	private OwnerUserDataService ownerUserDataService;

	// ===========OWNER===========================================
	@RequestMapping("/Admin/home")
	public ModelAndView home() {
		HashMap<String, Object> model = ownerUserDataService.getMainData();
		return new ModelAndView("/Admin/Home", "model", model);
	}

	// =====================USER===========================================
	@RequestMapping("/User/userHome")
	public ModelAndView userHome() {
		Map<String, Object> model = new HashMap<>();
		SecurityUser myUser = userDetailsService.getCurrentUser();
		if (myUser != null) {
			model.put("user", myUser);

		} else {
			return new ModelAndView("/User/UserHome");
		}

		return new ModelAndView("/User/UserHome", "model", model);
	}

	@RequestMapping("/User/userReadings")
	public ModelAndView aparmtnetRest(@RequestParam(value = "media") String media) {
		HashMap<String, Object> model = new HashMap<>();
		Apartment ap = userDetailsService.getCurrentUser().getTenant().getApartment();
		switch (media) {
		case "energy":
			model.put("media", "Energia");
			model.put("readings", ownerUserDataService.getReadingsForTenant(ap, Media.ENERGY));
			break;
		case "gas":
			model.put("media", "Gas");
			model.put("readings", ownerUserDataService.getReadingsForTenant(ap, Media.GAS));
			break;
		case "water":
			model.put("media", "Woda");
			model.put("readings", ownerUserDataService.getReadingsForTenant(ap, Media.WATER));
			break;
		default:
			break;
		}
	
		return new ModelAndView("/User/UserReadings", "model", model);
	}

	@RequestMapping("/User/userPayment")
	public ModelAndView userPayment() {
		Map<String, Object> model = new HashMap<>();
		Tenant tenant = userDetailsService.getCurrentUser().getTenant();

		model.put("energy", paymentService.getPaymentForTenant(tenant, Media.ENERGY));
		model.put("water", paymentService.getPaymentForTenant(tenant, Media.GAS));
		model.put("gas", paymentService.getPaymentForTenant(tenant, Media.WATER));

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
		if (!newPassword.equals(newPassword2) || Objects.equals(newPassword, "") || Objects.equals(newPassword2, "")) {
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
