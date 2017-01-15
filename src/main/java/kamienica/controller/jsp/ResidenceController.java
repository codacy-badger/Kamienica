package kamienica.controller.jsp;

import kamienica.core.util.CommonUtils;
import kamienica.feature.residence.ResidenceService;
import kamienica.model.Residence;
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
    @Autowired
    ResidenceService service;

    @RequestMapping(value = "/residence", method = RequestMethod.GET)
    public ModelAndView list() {
        Map<String, Object> model = new HashMap<>();
        //final Residence res = CommonUtils.fetchSecurityUser().getResidence();
        //model.put("residence", res);
        return new ModelAndView("/Admin/Residence/residence", model);

    }


}
