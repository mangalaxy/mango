package com.mangalaxy.mango.service;

import com.mangalaxy.mango.model.dto.request.TalentRequest;
import com.mangalaxy.mango.model.dto.response.TalentResponse;
import com.mangalaxy.mango.model.entity.Talent;
import com.mangalaxy.mango.repository.TalentRepository;
import com.mangalaxy.mango.util.TalentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class TalentServiceImpl implements TalentService{

  private final TalentRepository talentRepository;
  private final ModelMapper modelMapper;

  @Override
  public Page<TalentResponse> findAll(Pageable pageable) {
    Page<Talent> talents = talentRepository.findAll(pageable);
    Page<TalentResponse> response = talents.map(talent -> modelMapper.map(talent, TalentResponse.class));
    return response;
  }

  @Override
  public TalentResponse getTalentById(Long id) {
    Talent talent = talentRepository.findById(id).orElseThrow(TalentNotFoundException::new);
    return modelMapper.map(talent, TalentResponse.class);
  }

  @Override
  public TalentResponse createNewTalent(TalentRequest talentRequest) {
    Talent talent = modelMapper.map(talentRequest, Talent.class);
    Talent createdTalent = talentRepository.save(talent);
    return modelMapper.map(createdTalent, TalentResponse.class);
  }

  @Override
  public TalentResponse updateTalent(TalentRequest talentRequest, Long id) {
    talentRequest.setId(id);
    Talent talent = modelMapper.map(talentRequest, Talent.class);
    Talent updatedTalent = talentRepository.save(talent);
    return modelMapper.map(updatedTalent, TalentResponse.class);
  }

  @Override
  public void deleteTalent(Long id) {
    Talent talent = talentRepository.findById(id).orElseThrow(TalentNotFoundException::new);
    talentRepository.delete(talent);
  }
}
