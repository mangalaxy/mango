package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.TalentSignUpRequest;
import com.mangalaxy.mango.domain.dto.request.TalentUpdateRequest;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TalentService {
  Page<TalentResponse> fetchTalentPage(Pageable pageable);
  boolean isEmailFree(String email);
  TalentResponse fetchTalentById(Long id);
  TalentResponse createNewTalent(TalentSignUpRequest talent);
  TalentResponse updateTalentById(Long id, TalentUpdateRequest talent);
  void deleteTalentById(Long id);
}
