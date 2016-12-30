package kamienica.controller.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Admin/Apartment")
public class ApartmentController {

	@RequestMapping(value = "/apartment", method = RequestMethod.GET)
	public ModelAndView apartmentList() {

		return new ModelAndView("/Admin/Apartment/apartment");

	}

	@RequestMapping(value = "expeption")
	public ModelAndView exception() throws Exception {
		throw new Exception("This should be shown..");

	}

}
