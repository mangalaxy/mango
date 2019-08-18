package com.mangalaxy.mango.service;

import com.mangalaxy.mango.model.dto.response.ProfileResponse;
import com.mangalaxy.mango.model.entity.Profile;
import com.mangalaxy.mango.repository.ProfileRepository;
import com.mangalaxy.mango.util.TalentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileServiceImpl implements ProfileService{
  private final ProfileRepository profileRepository;
  private final ModelMapper modelMapper;

  @Override
  public ProfileResponse getProfileByTalent(Long id) {
    Profile profile = profileRepository.findById(id).orElseThrow(TalentNotFoundException::new);
    return modelMapper.map(profile, ProfileResponse.class);
  }
}
