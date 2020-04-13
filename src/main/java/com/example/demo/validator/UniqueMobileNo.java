package com.example.demo.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD,ElementType.FIELD})
@Constraint(validatedBy = UniqueMobileNoValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueMobileNo {
	String message() default "Mobbile no. is allready exist";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
