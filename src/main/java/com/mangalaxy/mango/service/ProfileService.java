package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.response.ProfileResponse;

public interface ProfileService {
  ProfileResponse getProfileByTalent(Long id);
}
