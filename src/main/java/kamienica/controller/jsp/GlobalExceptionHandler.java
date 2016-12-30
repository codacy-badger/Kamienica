package kamienica.controller.jsp;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

	

	@ExceptionHandler(SQLException.class)
	public String handleSQLException(HttpServletRequest request, Exception ex) {
		return "database_error";
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public String noHandlerFoundException(HttpServletRequest request, Exception ex) {
		return "404";
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView generalException(Exception ex) {

		final HashMap<String, Object> model = new HashMap<>();
		model.put("exception", ex);
		return new ModelAndView("error",model);
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
