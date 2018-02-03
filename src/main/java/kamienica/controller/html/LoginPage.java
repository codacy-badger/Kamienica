package kamienica.controller.html;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPage {

    private static final String BASE_URL = "views/login.html";

    @RequestMapping("login")
    public ModelAndView login() {
        return new ModelAndView("login.html");
    }

    @RequestMapping(value = { "/", "/index", "" }, method = RequestMethod.GET)
    public String index() {
        return "login.html";
    }

    @RequestMapping(value = "/logout")
    public ModelAndView updatePassword(HttpServletRequest req, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(req, response, auth);
        }
        return new ModelAndView("login.html?logout");
    }

    @RequestMapping("/403")
    public ModelAndView accesDenied() {
        return new ModelAndView("403.html");
    }

    @RequestMapping("/404")
    public ModelAndView notExsists() {
        return new ModelAndView("404.html");
    }

}
