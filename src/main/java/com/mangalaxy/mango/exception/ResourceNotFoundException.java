package com.mangalaxy.mango.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException() {
    super("The resource with the specified ID does not exist");
  }

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
