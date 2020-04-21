package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.dto.UserRegisterDetailsRequest;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {

  @Override
  public boolean isValid(Object user, ConstraintValidatorContext context) {
    String password = ((UserRegisterDetailsRequest)user).getPassword();
    String confirmPassword = ((UserRegisterDetailsRequest)user).getConfirmPassword();
    return password.equals(confirmPassword);
  }

}
