package kamienica.controller.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Admin/Residence")
public class ResidenceController {

    @RequestMapping(value = "/residence", method = RequestMethod.GET)
    public ModelAndView apartmentList() {

        return new ModelAndView("/Admin/Residence/residence");

    }
}
