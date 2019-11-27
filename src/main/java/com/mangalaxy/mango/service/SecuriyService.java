package com.mangalaxy.mango.service;

public interface SecuriyService {
  String validatePasswordResetToken(long id, String token);
}
