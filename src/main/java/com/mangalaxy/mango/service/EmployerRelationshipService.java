package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployerRelationshipService {

  EmployerResponse matchTalentToEmployer(Long employerId, Long talentId, boolean isMatch);

  Page<TalentResponse> getMatchedTalentsForEmployerJob(Long employerId, Long jobId, Pageable pageable);

}
