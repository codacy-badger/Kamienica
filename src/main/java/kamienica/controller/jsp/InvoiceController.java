package kamienica.controller.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InvoiceController {

	@RequestMapping(value = "/Admin/Invoice/energy")
	public ModelAndView energyRest() {
		return new ModelAndView("/Admin/Invoice/energy");
	}

	@RequestMapping(value = "/Admin/Invoice/gas")
	public ModelAndView gasRest() {
		return new ModelAndView("/Admin/Invoice/gas");
	}

	@RequestMapping(value = "/Admin/Invoice/water")
	public ModelAndView waterRest() {
		return new ModelAndView("/Admin/Invoice/water");
	}
}
