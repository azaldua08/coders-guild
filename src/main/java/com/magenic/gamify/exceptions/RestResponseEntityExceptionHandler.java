package com.magenic.gamify.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.magenic.gamify.model.Employee;

// https://www.baeldung.com/exception-handling-for-rest-with-spring#controlleradvice
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	// https://stackoverflow.com/questions/43502332/how-to-get-the-requestbody-in-an-exceptionhandler-spring-rest
	@Autowired
    private RequestContext requestContext;
	
	@ExceptionHandler(value = { TransactionSystemException.class, RollbackException.class, 
			ConstraintViolationException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, HttpServletRequest request) {
		List requestBody = (List) requestContext.getRequestBody();
		
		ConstraintViolationException cex= (ConstraintViolationException) ex.getCause().getCause();
		boolean partiallyCreated = false;
		List<String> errors = new ArrayList<String>();
		for(ConstraintViolation violation: cex.getConstraintViolations()) {
			String rootBeanUsername = ((Employee)violation.getRootBean()).getUsername();
			if (requestBody != null ) {
				String firstUsername = ((Employee) requestBody.get(0)).getUsername();
				partiallyCreated = !rootBeanUsername.equals(firstUsername);
			}
			errors.add("Failed to create employee with username " + rootBeanUsername
					+ " Error: "  + violation.getMessage());
		}
		HttpHeaders headers = new HttpHeaders();
		if (partiallyCreated) {
			headers.add("partiallyCreated", "true");
		}
		return new ResponseEntity(errors, headers, HttpStatus.BAD_REQUEST);
	}
}
