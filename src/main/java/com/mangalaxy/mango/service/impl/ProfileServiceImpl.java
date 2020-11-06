package com.mangalaxy.mango.service.impl;

import com.mangalaxy.mango.domain.dto.request.ProfileRequest;
import com.mangalaxy.mango.domain.dto.response.ProfileResponse;
import com.mangalaxy.mango.domain.entity.Profile;
import com.mangalaxy.mango.domain.entity.Talent;
import com.mangalaxy.mango.exception.TalentNotFoundException;
import com.mangalaxy.mango.repository.ProfileRepository;
import com.mangalaxy.mango.service.ProfileService;
import com.mangalaxy.mango.service.TalentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {
  private final ProfileRepository profileRepository;
  private final ModelMapper modelMapper;
  private final TalentService talentService;

  @Override
  @Transactional(readOnly = true)
  public ProfileResponse fetchTalentProfile(Long talentId) {
    Profile profile = profileRepository.findById(talentId).orElseThrow(TalentNotFoundException::new);
    return modelMapper.map(profile, ProfileResponse.class);
  }

  @Override
  public ProfileResponse fetchAuthorizedTalentProfile() {
    Talent currentTalent = talentService.getPrincipalTalent();
    Profile profile = profileRepository.findById(currentTalent.getId()).orElseThrow(TalentNotFoundException::new);
    return modelMapper.map(profile, ProfileResponse.class);
  }

  @Override
  public ProfileResponse updateAuthorizedTalentProfile(ProfileRequest profileRequest) {
    Talent currentTalent = talentService.getPrincipalTalent();
    Profile profile = modelMapper.map(profileRequest, Profile.class);
    profile.setId(currentTalent.getId());
    Profile updatedProfile = profileRepository.save(profile);
    return modelMapper.map(updatedProfile, ProfileResponse.class);
  }

}
