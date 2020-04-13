package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupInfo {
	
	private String userName;
	private String password;
	private Boolean Active;
	private String roles;
}
