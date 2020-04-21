package com.example.demo.exception;

public class IncorrectCredentials extends Exception  {

  private static final long serialVersionUID = 1L;

  public IncorrectCredentials(String errorMsg) {
    super(errorMsg);
  }

}
