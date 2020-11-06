package com.mangalaxy.mango.exception;

public class TalentNotFoundException extends RuntimeException {

  public TalentNotFoundException() {
    super("Talent with given id not found");
  }

  public TalentNotFoundException(String message) {
    super(message);
  }
}
