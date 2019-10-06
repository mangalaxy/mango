package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.ProfileRequest;
import com.mangalaxy.mango.domain.dto.response.ProfileResponse;
import com.mangalaxy.mango.domain.entity.Profile;
import com.mangalaxy.mango.domain.entity.Talent;
import com.mangalaxy.mango.repository.ProfileRepository;
import com.mangalaxy.mango.util.TalentNotFoundException;
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

  @Transactional(readOnly = true)
  @Override
  public ProfileResponse getProfileByTalent(Long id) {
    Profile profile = profileRepository.findById(id).orElseThrow(TalentNotFoundException::new);
    return modelMapper.map(profile, ProfileResponse.class);
  }

  @Override
  public ProfileResponse getCurrentTalentProfile() {
    Talent currentTalent = talentService.getPrincipalTalent();
    Profile profile = profileRepository.findById(currentTalent.getId()).orElseThrow(TalentNotFoundException::new);
    return modelMapper.map(profile, ProfileResponse.class);
  }

  @Override
  public ProfileResponse updateCurrentProfile(ProfileRequest profileRequest) {
    Talent currentTalent = talentService.getPrincipalTalent();
    Profile profile = modelMapper.map(profileRequest, Profile.class);
    profile.setId(currentTalent.getId());
    Profile updatedProfile = profileRepository.save(profile);
    return modelMapper.map(updatedProfile, ProfileResponse.class);
  }

}
