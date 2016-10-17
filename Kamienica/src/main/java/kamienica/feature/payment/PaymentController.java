package kamienica.feature.payment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class PaymentController {

	@RequestMapping("/Admin/Payment/payment")
	public ModelAndView list() {
		return new ModelAndView("/Admin/Payment/payment");

	}

}
