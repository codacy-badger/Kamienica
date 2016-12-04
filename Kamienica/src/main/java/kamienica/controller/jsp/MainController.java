package kamienica.controller.jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kamienica.model.Tenant;

@Controller
public class MainController {

	@RequestMapping("login")
	public ModelAndView login(@ModelAttribute("tenant") Tenant tenant, BindingResult result) {
		return new ModelAndView("Login");
	}

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index() {
		return "Login";
	}

	@RequestMapping(value = "/logout")
	public ModelAndView updatePassword(HttpServletRequest req, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(req, response, auth);
		}
		return new ModelAndView("redirect:/login?logout");
	}

	@RequestMapping("/403")
	public ModelAndView accesDenied() {
		return new ModelAndView("403");
	}

	@RequestMapping("/404")
	public ModelAndView notExsists() {
		return new ModelAndView("404");
	}

}