package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.TalentRequest;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.domain.entity.Talent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TalentService {
  Page<TalentResponse> fetchTalentPage(Pageable pageable);
  TalentResponse fetchTalentById(Long id);
  TalentResponse createNewTalent(TalentRequest talent);
  TalentResponse updateTalentById(Long id, TalentRequest talent);
  void deleteTalentById(Long id);
  TalentResponse getCurrentTalent();
  TalentResponse updateCurrentTalent(TalentRequest talent);
  void deleteCurrentTalent();
  Talent getPrincipalTalent();

}
