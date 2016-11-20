package kamienica.controller.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Admin/Tenant")
public class TenantController {

	@RequestMapping(value = "/tenant", method = RequestMethod.GET)
	public ModelAndView apartmentList2() {

		return new ModelAndView("/Admin/Tenant/tenant");

	}

}
