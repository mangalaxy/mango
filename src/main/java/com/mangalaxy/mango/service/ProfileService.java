package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.response.ProfileResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProfileService {
  ProfileResponse getProfileByTalent(Long id);
}
