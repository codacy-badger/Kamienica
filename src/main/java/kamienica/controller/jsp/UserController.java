package kamienica.controller.jsp;

import kamienica.feature.owner.IOwnerUserDataService;
import kamienica.feature.payment.IPaymentService;
import kamienica.feature.security.ISecurityService;
import kamienica.model.entity.Apartment;
import kamienica.model.entity.Tenant;
import kamienica.model.enums.Media;
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
public class UserController {

	@Autowired
	private IPaymentService IPaymentService;
	@Autowired
	private ISecurityService securityService;
	@Autowired
	private IOwnerUserDataService ownerUserDataService;

	// ===========OWNER===========================================
	@RequestMapping("/Admin/home")
	public ModelAndView home() {
		HashMap<String, Object> model = ownerUserDataService.getMainData();
		return new ModelAndView("/Admin/Home", "model", model);
	}

	// =====================TENANT===========================================
	@RequestMapping("/User/userHome")
	public ModelAndView userHome() {
		Map<String, Object> model = new HashMap<>();
		final Tenant t = ownerUserDataService.getLoggedTenant();
		if (t != null) {
			model.put("user", t);

		} else {
			return new ModelAndView("/User/UserHome");
		}

		return new ModelAndView("/User/UserHome", "model", model);
	}

	@RequestMapping("/User/userReadings")
	public ModelAndView aparmtnetRest(@RequestParam(value = "media") String media) {
		HashMap<String, Object> model = new HashMap<>();
		Apartment ap = ownerUserDataService.getLoggedTenant().fetchApartment();
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
		final Tenant tenant = ownerUserDataService.getLoggedTenant();

		model.put("energy", IPaymentService.getPaymentForTenant(tenant, Media.ENERGY));
		model.put("water", IPaymentService.getPaymentForTenant(tenant, Media.GAS));
		model.put("gas", IPaymentService.getPaymentForTenant(tenant, Media.WATER));

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
			securityService.changePassword(email, oldPassword, newPassword);
		} catch (UsernameNotFoundException e) {
			model.put("error", "Niepoprawny login lub hasło");
			return new ModelAndView("/User/UserPassword", "model", model);
		}
		model.put("class", "alert-success");
		model.put("msg", "Hasło zostało zmienione");
		model.put("user", ownerUserDataService.getLoggedTenant());
		return new ModelAndView("/User/UserHome", "model", model);
	}

}
