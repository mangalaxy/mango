package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.util.ApiError;
import com.mangalaxy.mango.util.AppException;
import com.mangalaxy.mango.util.BadRequestException;
import com.mangalaxy.mango.util.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.attribute.UserPrincipalNotFoundException;

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

  @ExceptionHandler({ResourceNotFoundException.class, UserPrincipalNotFoundException.class})
  protected ResponseEntity<Object> handleEntityNotFoundEx(ResourceNotFoundException ex, WebRequest request) {
    ApiError apiError = new ApiError();
    apiError.setHttpStatus(HttpStatus.NOT_FOUND);
    apiError.setMessage("Entity not found");
    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({BadRequestException.class})
  protected ResponseEntity<Object> handleBadRequesrEx(BadRequestException ex, WebRequest request) {
    ApiError apiError = new ApiError();
    apiError.setHttpStatus(HttpStatus.BAD_REQUEST);
    apiError.setMessage(ex.getMessage());
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({AppException.class})
  protected ResponseEntity<Object> handleAppEx(AppException ex, WebRequest request) {
    ApiError apiError = new ApiError();
    apiError.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    apiError.setMessage(ex.getMessage());
    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    ApiError apiError = new ApiError();
    apiError.setMessage(ex.getMessage());
    apiError.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
