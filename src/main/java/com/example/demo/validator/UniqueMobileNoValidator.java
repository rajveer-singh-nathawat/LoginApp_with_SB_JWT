package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.repository.UserProfileRepository;

public class UniqueMobileNoValidator implements ConstraintValidator<UniqueMobileNo, String> {

	@Autowired
	private UserProfileRepository userRegisterDetailRepo;
	@Override
	public boolean isValid(String mobileNo, ConstraintValidatorContext context) {
		return (mobileNo != null && userRegisterDetailRepo.findByMobileNo(mobileNo)==null);
	}

}
