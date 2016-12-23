package kamienica.controller.jsp;

import kamienica.core.enums.Media;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class PaymentController {

	@RequestMapping("/Admin/Payment/payment")
	public ModelAndView list(@RequestParam Media media) {

		switch (media) {
		case ENERGY:
			return new ModelAndView("/Admin/Payment/energy");
		case GAS:
			return new ModelAndView("/Admin/Payment/gas");
		case WATER:
			return new ModelAndView("/Admin/Payment/water");
		default:
			return new ModelAndView("/Admin/Payment/energy");
		}

	}

}
