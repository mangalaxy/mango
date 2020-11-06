package com.mangalaxy.mango.web;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiValidationError extends ApiSubError {
  private String field;
  private Object rejectedValue;
  private String message;

  ApiValidationError(String object, String message) {
    super(object);
    this.message = message;
  }

  public ApiValidationError(String object, String field, Object rejectedValue, String message) {
    super(object);
    this.field = field;
    this.rejectedValue = rejectedValue;
    this.message = message;
  }
}
