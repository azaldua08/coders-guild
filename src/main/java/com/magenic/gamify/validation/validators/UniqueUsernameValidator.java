package com.magenic.gamify.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.magenic.gamify.services.EmployeeDetailsService;
import com.magenic.gamify.validation.constraints.UniqueUsername;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>{
	

	@Autowired
	private EmployeeDetailsService employeeDetailsService;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return value != null && employeeDetailsService.retrieveEmployeeByUsername(value) == null;
	}

}
