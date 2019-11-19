package com.mangalaxy.mango.util;

public class EmailNotConfirmedException extends RuntimeException {
  public EmailNotConfirmedException() {
    super("User email did not confirmed");
  }

  public EmailNotConfirmedException(String message) {
    super(message);
  }
}
