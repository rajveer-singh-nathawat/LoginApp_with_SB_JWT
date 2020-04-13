package com.example.demo.homecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthenticationRequest;
import com.example.demo.service.HomeService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController()
@RequestMapping()
public class LoginController {
	@Autowired
	private HomeService  homeService;

	@PostMapping(path="/signin")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		ResponseEntity<?> createdToken = homeService.createAuthenticationToken(authenticationRequest);
		return createdToken;		
	}
	
}
