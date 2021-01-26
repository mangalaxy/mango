package com.mangalaxy.mango.service.impl;

import com.mangalaxy.mango.domain.dto.request.ProfileRequest;
import com.mangalaxy.mango.domain.dto.response.ProfileResponse;
import com.mangalaxy.mango.domain.entity.Profile;
import com.mangalaxy.mango.exception.ResourceNotFoundException;
import com.mangalaxy.mango.repository.ProfileRepository;
import com.mangalaxy.mango.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {
  private final ProfileRepository profileRepository;
  private final ModelMapper modelMapper;

  @Override
  @Transactional(readOnly = true)
  public ProfileResponse fetchTalentProfile(Long talentId) {
    Profile profile = profileRepository.findById(talentId)
          .orElseThrow(() -> new ResourceNotFoundException("profile", "id", talentId));
    return modelMapper.map(profile, ProfileResponse.class);
  }

  @Override
  @Transactional
  public ProfileResponse updateProfileByTalentId(Long talentId, ProfileRequest profileRequest) {
    // TODO: Implement update function for talent profile
    return null;
  }

}
