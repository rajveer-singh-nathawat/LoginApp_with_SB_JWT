package com.example.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.demo.validator.PasswordValid;
import com.example.demo.validator.UniqueEmailValid;
import com.example.demo.validator.UniqueMobileNo;
import com.example.demo.validator.UniqueUserName;

public class UserRegisterDetailsRequest {
	@Pattern(regexp = "^[A-Za-z\\s]{0,25}$")
	private String fullName;
	@UniqueEmailValid
	@Email
	private String email;
	@Size(max = 12)
	@Pattern(regexp = "^[0-9]*$")
	@UniqueMobileNo
	private String mobileNo;
	@Pattern(regexp = "^[A-Za-z]{4,8}\\d{2}$")
	@UniqueUserName
	private String userName;
	@PasswordValid
	private String password;
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
