package com.mangalaxy.mango.util;

public class EmployerNotFoundExeption extends RuntimeException{
  public EmployerNotFoundExeption() {
    super("Employer with given id not found");
  }

  public EmployerNotFoundExeption(String message) {
    super(message);
  }
}
