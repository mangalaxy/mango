package com.mangalaxy.mango.service;

import com.mangalaxy.mango.model.entity.Profile;
import com.mangalaxy.mango.model.entity.Talent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TalentService {

  Page<Talent> findAll(Pageable pageable);

  Profile getProfileByTalent(Long id);
}
