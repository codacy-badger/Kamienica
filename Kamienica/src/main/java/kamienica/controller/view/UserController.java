package kamienica.controller.view;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kamienica.model.MyUser;
import kamienica.model.Tenant;
import kamienica.service.MyUserDetailsService;
import kamienica.service.PaymentService;
import kamienica.service.ReadingService;
import kamienica.service.TenantService;

@Controller
@RequestMapping("/User")
public class UserController {

	@Autowired
	private PaymentService paymentService;
	@Autowired
	private TenantService tenantService;
	@Autowired
	private ReadingService readingService;
	// temporary solution
	@Autowired
	private MyUserDetailsService userDetailsService;

	@RequestMapping("/userHome")
	public ModelAndView userHome() {
		Map<String, Object> model = new HashMap<String, Object>();
		MyUser myUser = getMyUser();
		if (myUser != null) {
			model.put("user", myUser);

		} else {
			return new ModelAndView("/User/UserHome");
		}
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		// if (!(auth instanceof AnonymousAuthenticationToken)) {
		// UserDetails userDetail = (UserDetails) auth.getPrincipal();
		// model.put("username", userDetail.getUsername());
		// }
		return new ModelAndView("/User/UserHome", "model", model);
	}

	@RequestMapping("/userReadings")
	public ModelAndView aparmtnetRest(@RequestParam(value = "media") String media) {
		HashMap<String, Object> model = new HashMap<>();
		if (media.equals("energy")) {
			Tenant tenant = tenantService.loadByMail(getMyUser().getUsername());
			model.put("media", "Energia");
			model.put("readings", readingService.getReadingEnergyForTenant(tenant.getApartment()));
		}
		if (media.equals("gas")) {
			Tenant tenant = tenantService.loadByMail(getMyUser().getUsername());
			model.put("media", "Energia");
			model.put("readings", readingService.getReadingGasForTenant(tenant.getApartment()));
		}
		if (media.equals("water")) {
			Tenant tenant = tenantService.loadByMail(getMyUser().getUsername());
			model.put("media", "Energia");
			model.put("readings", readingService.getReadingWaterForTenant(tenant.getApartment()));
		}
		return new ModelAndView("/User/UserReadings", "model", model);
	}

	@RequestMapping("/userPayment")
	public ModelAndView userPayment() {
		Map<String, Object> model = new HashMap<String, Object>();
		Tenant tenant = tenantService.loadByMail(getMyUser().getUsername());
		model.put("energy", paymentService.getPaymentEnergyForTenant(tenant));

		model.put("water", paymentService.getPaymentWaterForTenant(tenant));

		model.put("gas", paymentService.getPaymentGasForTenant(tenant));

		return new ModelAndView("/User/UserPayment", "model", model);
	}

	@RequestMapping("/userPassword")
	public ModelAndView changePassword() {
		return new ModelAndView("/User/UserPassword");
	}

	@RequestMapping("/userUpdatePassword")
	public ModelAndView updatePassword(HttpServletRequest req, @RequestParam String email,
			@RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String newPassword2) {
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

		return new ModelAndView("redirect:/User/userHome");
	}

	private MyUser getMyUser() {

		UserDetails userDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		try {
			return (MyUser) userDetailsService.loadUserByUsername(userDetail.getUsername());
		} catch (UsernameNotFoundException e) {
			return null;
		}
	}
}
