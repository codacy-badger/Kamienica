package kamienica.controller.jsp;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	

	@ExceptionHandler(SQLException.class)
	public String handleSQLException(HttpServletRequest request, Exception ex) {
		return "database_error";
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public String ggg(HttpServletRequest request, Exception ex) {
		return "404";
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
