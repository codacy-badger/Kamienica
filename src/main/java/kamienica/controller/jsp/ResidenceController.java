package kamienica.controller.jsp;

import kamienica.core.util.CommonUtils;
import kamienica.model.Residence;
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
    public ModelAndView apartmentList() {
        Map<String, Object> model = new HashMap<>();
        final Residence res = CommonUtils.fetchSecurityUser().getResidence();
        model.put("residence", res);
        return new ModelAndView("/Admin/Residence/residence", model);

    }


}
