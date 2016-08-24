package kamienica.controller.jsp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import kamienica.core.ApiResponse;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "This should be application specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(value = { Exception.class, })
	protected ResponseEntity<Object> handleOtherConflict(RuntimeException ex, WebRequest request) {
		System.out.println("--------");
		System.out.println(ex);
		String bodyOfResponse = "Bląd generalny";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(value = { ConstraintViolationException.class, })
	protected ResponseEntity<Object> handleConstraint(RuntimeException ex, WebRequest request) {

		return handleExceptionInternal(ex, "Constaint Violation Error", new HttpHeaders(), HttpStatus.CONFLICT,
				request);
	}

}