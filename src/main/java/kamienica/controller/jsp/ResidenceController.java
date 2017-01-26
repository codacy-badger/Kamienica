package kamienica.controller.jsp;

import kamienica.feature.residence.ResidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/Admin/Residence")
public class ResidenceController {

    @RequestMapping(value = "/residence", method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("/Admin/Residence/residence");

    }


}
