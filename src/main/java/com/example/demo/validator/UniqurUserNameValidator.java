package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repository.UserReository;

public class UniqurUserNameValidator implements ConstraintValidator<UniqueUserName, String> {
@Autowired
private UserReository userReository;
	@Override
	public boolean isValid(String userName, ConstraintValidatorContext context) {
		return (!userReository.findByUserName(userName).isPresent());
	}

}
