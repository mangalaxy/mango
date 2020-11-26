package com.mangalaxy.mango.service.impl;

import com.mangalaxy.mango.domain.dto.request.PasswordChangeRequest;
import com.mangalaxy.mango.domain.dto.response.ApiResponse;
import com.mangalaxy.mango.service.PasswordManagementService;
import org.springframework.stereotype.Service;

@Service
public class PasswordManagementServiceImpl implements PasswordManagementService {

  @Override
  public ApiResponse changePassword(Long userId, PasswordChangeRequest passwordPair) {
    return null;
  }

  @Override
  public ApiResponse resetPassword(String email) {
    return null;
  }

  @Override
  public ApiResponse setPassword(String passwordToken, String newPassword) {
    return null;
  }
}
