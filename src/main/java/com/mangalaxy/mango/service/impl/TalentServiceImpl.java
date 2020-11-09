package com.mangalaxy.mango.service.impl;

import com.mangalaxy.mango.domain.dto.request.TalentRequest;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.domain.entity.Talent;
import com.mangalaxy.mango.exception.ResourceNotFoundException;
import com.mangalaxy.mango.repository.TalentRepository;
import com.mangalaxy.mango.service.TalentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TalentServiceImpl implements TalentService {
  private final TalentRepository talentRepository;
  private final ModelMapper modelMapper;

  @Transactional
  @Override
  public Talent getPrincipalTalent() {
    return talentRepository.findAll().get(0);
  }

  @Transactional(readOnly = true)
  @Override
  public Page<TalentResponse> fetchTalentPage(Pageable pageable) {
    Page<Talent> talents = talentRepository.findAll(pageable);
    return talents.map(talent -> modelMapper.map(talent, TalentResponse.class));
  }

  @Transactional(readOnly = true)
  @Override
  public TalentResponse fetchTalentById(Long id) {
    Talent talent = findTalent(id);
    log.info("Fetched talent {}", talent);
    return modelMapper.map(talent, TalentResponse.class);
  }

  @Transactional
  @Override
  public TalentResponse createNewTalent(TalentRequest talentRequest) {
    Talent talent = modelMapper.map(talentRequest, Talent.class);
    talent = talentRepository.save(talent);
    log.info("Saved instance as: {}", talent);
    return modelMapper.map(talent, TalentResponse.class);
  }

  @Transactional
  @Override
  public TalentResponse updateTalentById(Long id, TalentRequest talentRequest) {
    Talent talent = findTalent(id);
    modelMapper.map(talentRequest, talent);
    Talent updatedTalent = talentRepository.save(talent);
    return modelMapper.map(updatedTalent, TalentResponse.class);
  }

  @Transactional
  @Override
  public void deleteTalentById(Long id) {
    Talent talent = findTalent(id);
    talentRepository.delete(talent);
    log.info("The talent was deleted by id={}", id);
  }

  @Transactional
  @Override
  public TalentResponse getCurrentTalent() {
    Talent currentTalent = getPrincipalTalent();
    return modelMapper.map(currentTalent, TalentResponse.class);
  }

  @Transactional
  @Override
  public TalentResponse updateCurrentTalent(TalentRequest talentRequest) {
    Talent talent = modelMapper.map(talentRequest, Talent.class);
    talent.setId(getPrincipalTalent().getId());
    Talent updatedTalent = talentRepository.save(talent);
    return modelMapper.map(updatedTalent, TalentResponse.class);
  }

  @Transactional
  @Override
  public void deleteCurrentTalent() {
    talentRepository.delete(getPrincipalTalent());
  }

  private Talent findTalent(Long id) {
    return talentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
  }
}
