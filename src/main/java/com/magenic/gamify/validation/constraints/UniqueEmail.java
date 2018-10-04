package com.magenic.gamify.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.magenic.gamify.validation.validators.UniqueEmailDelegate;

@Documented
@Constraint(validatedBy = UniqueEmailDelegate.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
	public String message() default "Email address already in use!";
	
	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default{};
}
