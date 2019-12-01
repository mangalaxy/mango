package com.mangalaxy.mango.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException() {
    super("Resource with specified ID not found");
  }

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
