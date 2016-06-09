package kamienica.feature.payment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@RequestMapping("/Admin/Payment/paymentList")
	public ModelAndView paymentList() {
		return new ModelAndView("/Admin/Payment/PaymentList");
	}

	// ------------------------------PAYMENTLIST--------------------------------------------------
	@RequestMapping("/Admin/Payment/paymentEnergyList")
	public ModelAndView paymentEnergyList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("media", "Energia");
		model.put("url", "Energy");
		model.put("payment", paymentService.getPaymentEnergyList());
		return new ModelAndView("/Admin/Payment/PaymentList2", model);

	}

	@RequestMapping("/Admin/Payment/paymentGasList")
	public ModelAndView paymentGasList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("media", "Gaz");
		model.put("url", "Gas");
		model.put("payment", paymentService.getPaymentGasList());
		return new ModelAndView("/Admin/Payment/PaymentList2", model);

	}

	@RequestMapping("/Admin/Payment/paymentWaterList")
	public ModelAndView paymentWaterList() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("media", "Woda");
		model.put("url", "Water");
		model.put("payment", paymentService.getPaymentWaterList());
		return new ModelAndView("/Admin/Payment/PaymentList2", model);

	}

}
