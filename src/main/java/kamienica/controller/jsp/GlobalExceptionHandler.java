package kamienica.controller.jsp;

import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(JDBCException.class)
    public String handleSQLException(HttpServletRequest request, Exception ex) {

        LOG.error("SQL exception: ", ex);
        return "database_error";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String noHandlerFoundException(HttpServletRequest request, Exception ex) {

        LOG.error("No handler found: ", ex);
        return "404";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView generalException(HttpServletRequest request, Exception ex) {
        LOG.error("Exception: ", ex);
        final HashMap<String, Object> model = new HashMap<>();
        model.put("exception", ex.getMessage());
        return new ModelAndView("error", "model", model);
    }

//	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
//	@ExceptionHandler(NoHandlerFoundException.class)
//	 public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
//        ModelAndView mav = new ModelAndView("/404");
//        mav.addObject("exception", e);  
//        //mav.addObject("errorcode", "404");
//        return mav;
//}
}
