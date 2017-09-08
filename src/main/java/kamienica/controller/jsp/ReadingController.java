package kamienica.controller.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Admin/Reading")
public class ReadingController {

    @RequestMapping("/energyRest")
    public ModelAndView energyRest() {
        return new ModelAndView("/Admin/Reading/energyRest");
    }

    @RequestMapping("/gasRest")
    public ModelAndView gasRest() {
        return new ModelAndView("/Admin/Reading/gasRest");
    }

    @RequestMapping("/waterRest")
    public ModelAndView waterRest() {
        return new ModelAndView("/Admin/Reading/waterRest");
    }

    @RequestMapping("/rest")
    public ModelAndView list() {
        return new ModelAndView("/Admin/Reading/readings");
    }
}
