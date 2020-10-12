package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.ProfileRequest;
import com.mangalaxy.mango.domain.dto.response.ProfileResponse;

public interface ProfileService {

  ProfileResponse fetchTalentProfile(Long talentId);

  ProfileResponse fetchAuthorizedTalentProfile();

  ProfileResponse updateAuthorizedTalentProfile(ProfileRequest profile);
}
