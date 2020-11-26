package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.PasswordChangeRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;

public interface PasswordManagementService {
  ApiResponse changePassword(Long userId, PasswordChangeRequest passwordPair);
  ApiResponse resetPassword(String email);
  ApiResponse setPassword(String passwordToken, String password);
}
