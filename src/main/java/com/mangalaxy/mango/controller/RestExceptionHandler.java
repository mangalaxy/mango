package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.util.ApiError;
import com.mangalaxy.mango.util.AppException;
import com.mangalaxy.mango.util.BadRequestException;
import com.mangalaxy.mango.util.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {
    ApiError error = new ApiError(status, ex.getMessage(), ex.getBindingResult().getFieldErrors());
    log.debug("Arguments not valid: {}", error);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ ResourceNotFoundException.class })
  protected ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest req) {
    ApiError error = new ApiError(HttpStatus.NOT_FOUND, "Resource not found", Collections.emptyList());
    log.debug("Resource not found: {}", error);
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({BadRequestException.class})
  protected ResponseEntity<Object> handleBadRequest(BadRequestException ex, WebRequest req) {
    ApiError error = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), Collections.emptyList());
    log.debug("Bad request: {}", error);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({AppException.class})
  protected ResponseEntity<Object> handleApplication(AppException ex, WebRequest req) {
    ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), Collections.emptyList());
    log.debug("Application error: {}", error);
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
