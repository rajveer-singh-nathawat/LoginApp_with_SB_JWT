package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserRegisterDetailsRequest;
import com.example.demo.service.HomeService;

@RestController
@RequestMapping
public class RegisterController {

  @Autowired
  private HomeService homeService;

  @PostMapping("/register")
  public ResponseEntity<HttpStatus> registerUser(
      @Valid @RequestBody UserRegisterDetailsRequest userRegisterDetailsRequest) {
    ResponseEntity<HttpStatus> status = homeService.registerUser(userRegisterDetailsRequest);
    return status;
  }
}
