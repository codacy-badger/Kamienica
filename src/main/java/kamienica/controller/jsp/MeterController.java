package kamienica.controller.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MeterController {

	@RequestMapping(value = "/Admin/Meter/energy")
	public ModelAndView energyRest() {

		return new ModelAndView("/Admin/Meter/energy");
	}
	
	@RequestMapping(value = "/Admin/Meter/gas")
	public ModelAndView gasRest() {

		return new ModelAndView("/Admin/Meter/gas");
	}
	
	@RequestMapping(value = "/Admin/Meter/water")
	public ModelAndView waterRest() {

		return new ModelAndView("/Admin/Meter/water");
	}
	

	
}