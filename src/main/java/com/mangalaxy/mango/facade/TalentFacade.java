package com.mangalaxy.mango.facade;

import com.mangalaxy.mango.dto.request.TalentRequest;
import com.mangalaxy.mango.dto.response.ProfileResponse;
import com.mangalaxy.mango.dto.response.TalentResponse;
import com.mangalaxy.mango.model.entity.Profile;
import com.mangalaxy.mango.model.entity.Talent;
import com.mangalaxy.mango.service.TalentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class TalentFacade extends AbstractDtoFacade<Talent, TalentRequest, TalentResponse> {

  private TalentService talentService;

  @Autowired
  public TalentFacade(TalentService talentService) {
    this.talentService = talentService;
  }

  public Page<TalentResponse> findAll(Pageable pageable) {
    return mapListOfEntitiesToResponseDtoList(talentService.findAll(pageable));
  }

  public ProfileResponse findProfileByOwner(Long id) {
    Profile profile = talentService.getProfileByTalent(id);
    return modelMapper().map(profile, ProfileResponse.class);
  }
}
