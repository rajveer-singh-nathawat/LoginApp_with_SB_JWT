package com.example.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.demo.validator.ConfirmPassword;
import com.example.demo.validator.PasswordValid;
import com.example.demo.validator.UniqueEmailValid;
import com.example.demo.validator.UniqueMobileNo;
import com.example.demo.validator.UniqueUserName;

import lombok.Data;
@Data
@ConfirmPassword
public class UserRegisterDetailsRequest {
	@Pattern(regexp = "^[A-Za-z\\s]{0,25}$")
	@NotEmpty(message = "please enter first name")
	private String fullName;
	@UniqueEmailValid
	@Email
	@NotEmpty(message = "please enter email")
	private String email;
	@Size(max = 12)
	@Pattern(regexp = "^[0-9]*$")
	@UniqueMobileNo
	@NotEmpty(message = "please enter mobile no.")
	private String mobileNo;
	@Pattern(regexp = "^[A-Za-z]{4,8}[0-9]{2}$")
	@UniqueUserName
	@NotEmpty(message = "please enter username")
	private String userName;
	@PasswordValid
	@NotEmpty(message = "please enter your password")
	private String password;
	@NotEmpty(message = "please confirm your password")
	private String confirmPassword;
}	
