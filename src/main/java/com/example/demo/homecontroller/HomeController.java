package com.example.demo.homecontroller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthenticationRequest;
import com.example.demo.dto.UserRegisterDetailsRequest;
import com.example.demo.model.ApiResponseEntity;
import com.example.demo.model.UserDetail;
import com.example.demo.service.HomeService;
import com.example.demo.service.UserDetailService;
import com.example.demo.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
@RestController()
@RequestMapping()
public class HomeController {
	

	
	
	@Autowired
	private HomeService homeService;

	

	@GetMapping("/user/data")
	public ResponseEntity<?> user(@RequestHeader HttpHeaders headers, HttpServletRequest request) {
			return new ResponseEntity<>(new ApiResponseEntity("Hello user",true), HttpStatus.OK);
//		}
//		return null;
	}

	@GetMapping("/admin/data")
	public ResponseEntity<?> admin(@RequestHeader HttpHeaders headers, HttpServletRequest request) {
		return new ResponseEntity<>(new ApiResponseEntity("Hello admin",true), HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<HttpStatus> registerUser(@Valid @RequestBody UserRegisterDetailsRequest userRegisterDetailsRequest){
		ResponseEntity<HttpStatus> status = homeService.registerUser(userRegisterDetailsRequest);
		return status;
	}

}
