package kamienica.feature.payment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kamienica.core.util.Media;

@Controller
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	// ------------------------------PAYMENTLIST--------------------------------------------------
	@RequestMapping("/Admin/Payment/paymentEnergyList")
	public ModelAndView paymentEnergyList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("media", "Energia");
		model.put("url", "Energy");
		model.put("payment", paymentService.getPaymentList(Media.ENERGY));
		return new ModelAndView("/Admin/Payment/PaymentList", model);

	}

	@RequestMapping("/Admin/Payment/paymentGasList")
	public ModelAndView paymentGasList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("media", "Gaz");
		model.put("url", "Gas");
		model.put("payment", paymentService.getPaymentList(Media.GAS));
		return new ModelAndView("/Admin/Payment/PaymentList", model);

	}

	@RequestMapping("/Admin/Payment/paymentWaterList")
	public ModelAndView paymentWaterList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("media", "Woda");
		model.put("url", "Water");
		model.put("payment", paymentService.getPaymentList(Media.WATER));
		return new ModelAndView("/Admin/Payment/PaymentList", model);

	}

}
