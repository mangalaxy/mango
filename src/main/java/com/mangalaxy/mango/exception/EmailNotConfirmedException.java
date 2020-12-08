package com.mangalaxy.mango.exception;

public class EmailNotConfirmedException extends RuntimeException {
  public EmailNotConfirmedException() {
    super("Email address was not confirmed");
  }

  public EmailNotConfirmedException(String message) {
    super(message);
  }
}
