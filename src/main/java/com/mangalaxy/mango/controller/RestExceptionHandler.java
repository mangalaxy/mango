package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.util.ApiError;
import com.mangalaxy.mango.util.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {
    ApiError apiError = new ApiError();
    apiError.setHttpStatus(status);
    apiError.setMessage(ex.getMessage());

    return new ResponseEntity<>(apiError, status);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers, HttpStatus status, WebRequest request) {
    ApiError apiError = new ApiError();
    apiError.setFieldErrors(ex.getBindingResult().getFieldErrors());
    apiError.setMessage(ex.getMessage());
    apiError.setHttpStatus(status);
    return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<Object> handleEntityNotFoundEx(EntityNotFoundException ex, WebRequest request) {
    ApiError apiError = new ApiError();
    apiError.setHttpStatus(HttpStatus.NOT_FOUND);
    apiError.setMessage("Entity not found");
    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    ApiError apiError = new ApiError();
    apiError.setMessage(ex.getMessage());
    apiError.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }



}
