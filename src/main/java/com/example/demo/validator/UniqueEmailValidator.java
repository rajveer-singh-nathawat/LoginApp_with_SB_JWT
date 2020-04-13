package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repository.UserProfileRepository;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailValid, String>{

	@Autowired
	private UserProfileRepository userRegisterDetailsRepository;
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return email != null && userRegisterDetailsRepository.findByEmail(email) ==null;
	}

}
