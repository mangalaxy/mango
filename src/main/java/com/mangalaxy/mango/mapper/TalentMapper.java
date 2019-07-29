package com.mangalaxy.mango.mapper;

import com.mangalaxy.mango.model.dto.request.TalentRequest;
import com.mangalaxy.mango.model.dto.response.ProfileResponse;
import com.mangalaxy.mango.model.dto.response.TalentResponse;
import com.mangalaxy.mango.model.entity.Profile;
import com.mangalaxy.mango.model.entity.Talent;
import com.mangalaxy.mango.service.TalentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TalentMapper extends AbstractDtoMapper<Talent, TalentRequest, TalentResponse> {

  private final TalentServiceImpl talentServiceImpl;

  public Page<TalentResponse> findAll(Pageable pageable) {
    return mapListOfEntitiesToResponseDtoList(talentServiceImpl.findAll(pageable));
  }

  public ProfileResponse findProfileByOwner(Long id) {
    Profile profile = talentServiceImpl.getProfileByTalent(id);
    return modelMapper().map(profile, ProfileResponse.class);
  }
}
