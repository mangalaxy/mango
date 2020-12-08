package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
public class LoginRequest {

  @NotBlank
  @Email
  @Size(max = 45)
  String email;

  @NotBlank
  @Size(min = 6, max = 100)
  String password;

  @JsonCreator
  public LoginRequest(@JsonProperty("email") String email,
                      @JsonProperty("password") String password) {
    this.email = email;
    this.password = password;
  }
}
