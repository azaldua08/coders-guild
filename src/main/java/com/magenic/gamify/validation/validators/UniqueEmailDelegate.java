package com.magenic.gamify.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.magenic.gamify.validation.constraints.UniqueEmail;

@Component
public class UniqueEmailDelegate implements ConstraintValidator<UniqueEmail, String> {

	 private static ApplicationContext applicationContext;
	    
	    private ConstraintValidator constraintValidator;
	    
	    /**
	     * Assure application context is set.
	     * @todo dirty, automatic injection of autowired stuff is not working
	     *
	     * @param applicationContext application context
	     */
	    @Autowired
	    public void setApplicationContext(ApplicationContext applicationContext) {
	    	UniqueEmailDelegate.applicationContext = applicationContext;
	    }
	 
	    @Override
	    public void initialize(UniqueEmail constraintAnnotation) {
	        constraintValidator = (ConstraintValidator) applicationContext.getBean("uniqueEmailValidator");
	        constraintValidator.initialize(constraintAnnotation);
	    }
	    
		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			// TODO Auto-generated method stub
			return constraintValidator.isValid(value, context);
		}

}
