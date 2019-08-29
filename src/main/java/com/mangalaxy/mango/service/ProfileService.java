package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.ProfileRequest;
import com.mangalaxy.mango.domain.dto.response.ProfileResponse;

public interface ProfileService {
  ProfileResponse getProfileByTalent(Long id);

  ProfileResponse getCurrentTalentProfile();

  ProfileResponse updateCurrentProfile(ProfileRequest profile);
}
