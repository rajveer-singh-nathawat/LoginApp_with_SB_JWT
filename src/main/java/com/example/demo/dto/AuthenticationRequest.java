package com.example.demo.dto;

public class AuthenticationRequest {
	private String userName;
	private String password;
	
	public AuthenticationRequest() {
		
	}
	
	public AuthenticationRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
