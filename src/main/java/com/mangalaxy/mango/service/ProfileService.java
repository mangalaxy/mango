package com.mangalaxy.mango.service;

import com.mangalaxy.mango.model.dto.response.ProfileResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProfileService {
  ProfileResponse getProfileByTalent(Long id);
}
