package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.TalentRequest;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.domain.entity.Talent;
import com.mangalaxy.mango.util.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TalentService {

  Page<TalentResponse> findAll(Pageable pageable) throws ResourceNotFoundException;

  TalentResponse getTalentById(Long id) throws ResourceNotFoundException;

  TalentResponse createNewTalent(TalentRequest talent);

  TalentResponse updateTalent(TalentRequest talent, Long id) throws ResourceNotFoundException;

  void deleteTalent(Long id) throws ResourceNotFoundException;

  TalentResponse getCurrentTalent();

  TalentResponse updateCurrentTalent(TalentRequest talent);

  void deleteCurrentTalent();

  public Talent getPrincipalTalent();

}
