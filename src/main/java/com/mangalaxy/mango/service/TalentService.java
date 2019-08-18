package com.mangalaxy.mango.service;

import com.mangalaxy.mango.model.dto.request.TalentRequest;
import com.mangalaxy.mango.model.dto.response.TalentResponse;
import com.mangalaxy.mango.model.entity.Profile;
import com.mangalaxy.mango.model.entity.Talent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TalentService {

  Page<TalentResponse> findAll(Pageable pageable);

  TalentResponse getTalentById(Long id);

  TalentResponse createNewTalent(TalentRequest talent);

  TalentResponse updateTalent(TalentRequest talent, Long id);

  void deleteTalent(Long id);
}
