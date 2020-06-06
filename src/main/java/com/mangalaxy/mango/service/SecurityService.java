package com.mangalaxy.mango.service;

public interface SecurityService {
  String validatePasswordResetToken(long id, String token);
}
