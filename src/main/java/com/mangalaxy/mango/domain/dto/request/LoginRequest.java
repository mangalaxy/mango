package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.Email;

@Value
public class LoginRequest {
  @Email
  String email;
  String password;

  @JsonCreator
  public LoginRequest(@JsonProperty("email") String email, @JsonProperty("password") String password) {
    this.email = email;
    this.password = password;
  }
}