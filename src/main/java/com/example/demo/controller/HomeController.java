package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ApiResponseEntity;
import com.example.demo.service.HomeService;

@RestController
@RequestMapping
public class HomeController {

  @Autowired
  private HomeService homeService;

  @GetMapping("/user/data")
  public ResponseEntity<?> user(@RequestHeader HttpHeaders headers, HttpServletRequest request) {
    return new ResponseEntity<>(new ApiResponseEntity("Hello user", true), HttpStatus.OK);
  }

  @GetMapping("/admin/data")
  public ResponseEntity<?> admin(@RequestHeader HttpHeaders headers, HttpServletRequest request) {
    return new ResponseEntity<>(new ApiResponseEntity("Hello admin", true), HttpStatus.OK);
  }

}
