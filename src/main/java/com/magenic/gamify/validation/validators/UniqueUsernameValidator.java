package com.magenic.gamify.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.magenic.gamify.services.EmployeeDetailsService;
import com.magenic.gamify.validation.constraints.UniqueUsername;

@Component
@Scope("prototype")
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>{
	

	@Autowired
	private EmployeeDetailsService employeeDetailsService;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return value != null && employeeDetailsService.retrieveEmployeeByUsername(value) == null;
	}

}
