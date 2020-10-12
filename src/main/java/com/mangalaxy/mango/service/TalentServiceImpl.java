package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.TalentRequest;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.domain.entity.Talent;
import com.mangalaxy.mango.repository.TalentRepository;
import com.mangalaxy.mango.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class TalentServiceImpl implements TalentService{
  private final TalentRepository talentRepository;
  private final ModelMapper modelMapper;

  @Override
  public Talent getPrincipalTalent() {
    return talentRepository.findAll().get(0);
  }

  @Override
  public Page<TalentResponse> findAll(Pageable pageable) throws ResourceNotFoundException {
    Page<Talent> talents = talentRepository.findAll(pageable);
    if (talents.isEmpty()) {
      throw new ResourceNotFoundException();
    }
    return talents.map(talent -> modelMapper.map(talent, TalentResponse.class));
  }

  @Override
  public TalentResponse getTalentById(Long id) throws ResourceNotFoundException {
    Talent talent = talentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    return modelMapper.map(talent, TalentResponse.class);
  }

  @Override
  public TalentResponse createNewTalent(TalentRequest talentRequest) {
    Talent talent = modelMapper.map(talentRequest, Talent.class);
    Talent createdTalent = talentRepository.save(talent);
    return modelMapper.map(createdTalent, TalentResponse.class);
  }

  @Override
  public TalentResponse updateTalent(TalentRequest talentRequest, Long id) throws ResourceNotFoundException {
    Talent talent = modelMapper.map(talentRequest, Talent.class);
    Talent talentFromDb = talentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    talent.setId(talentFromDb.getId());
    Talent updatedTalent = talentRepository.save(talent);
    return modelMapper.map(updatedTalent, TalentResponse.class);
  }

  @Override
  public void deleteTalent(Long id) throws ResourceNotFoundException {
    Talent talent = talentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    talentRepository.delete(talent);
  }

  @Override
  public TalentResponse getCurrentTalent() {
    Talent currentTalent = getPrincipalTalent();
    return modelMapper.map(currentTalent, TalentResponse.class);
  }


  @Override
  public TalentResponse updateCurrentTalent(TalentRequest talentRequest) {
    Talent talent = modelMapper.map(talentRequest, Talent.class);
    talent.setId(getPrincipalTalent().getId());
    Talent updatedTalent = talentRepository.save(talent);
    return modelMapper.map(updatedTalent, TalentResponse.class);
  }

  @Override
  public void deleteCurrentTalent() {
    talentRepository.delete(getPrincipalTalent());
  }
}
