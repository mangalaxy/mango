package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployerRelationshipService {
  boolean toggleTalentBookmark(Long employerId, Long talentId, boolean bookmarked);
  Page<TalentResponse> fetchBookmarkedTalents(Long employerId, Pageable pageable);
}
