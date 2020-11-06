package com.mangalaxy.mango.exception;

public class EmployerNotFoundException extends RuntimeException {

  public EmployerNotFoundException() {
    super("Employer with given id not found");
  }

  public EmployerNotFoundException(String message) {
    super(message);
  }
}
