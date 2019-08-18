package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.TalentRequest;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TalentService {

  Page<TalentResponse> findAll(Pageable pageable);

  TalentResponse getTalentById(Long id);

  TalentResponse createNewTalent(TalentRequest talent);

  TalentResponse updateTalent(TalentRequest talent, Long id);

  void deleteTalent(Long id);

}
