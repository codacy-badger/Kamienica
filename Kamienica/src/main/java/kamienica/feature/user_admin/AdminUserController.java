package kamienica.feature.user_admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kamienica.core.util.Media;
import kamienica.feature.apartment.Apartment;
import kamienica.feature.payment.PaymentService;
import kamienica.feature.tenant.Tenant;

@Controller
public class AdminUserController {

	@Autowired
	private PaymentService paymentService;
	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired
	private AdminUserService adminUserService;

	// ===========ADMIN===========================================
	@RequestMapping("/Admin/home")
	public ModelAndView home() {
		HashMap<String, Object> model = adminUserService.getMainData();
		return new ModelAndView("/Admin/Home", "model", model);
	}

	// =====================USER===========================================
	@RequestMapping("/User/userHome")
	public ModelAndView userHome() {
		Map<String, Object> model = new HashMap<String, Object>();
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
		Apartment ap = userDetailsService.getCurrentUser().getApartment();
		switch (media) {
		case "energy":
			model.put("media", "Energia");
			model.put("readings", adminUserService.getReadingsForTenant(ap, Media.ENERGY));
			break;
		case "gas":
			model.put("media", "Gas");
			model.put("readings", adminUserService.getReadingsForTenant(ap, Media.GAS));
			break;
		case "water":
			model.put("media", "Woda");
			model.put("readings", adminUserService.getReadingsForTenant(ap, Media.WATER));
			break;
		default:
			break;
		}
	
		return new ModelAndView("/User/UserReadings", "model", model);
	}

	@RequestMapping("/User/userPayment")
	public ModelAndView userPayment() {
		Map<String, Object> model = new HashMap<String, Object>();
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
