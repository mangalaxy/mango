package com.mangalaxy.mango.service.impl;

import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Talent;
import com.mangalaxy.mango.exception.EmployerNotFoundException;
import com.mangalaxy.mango.exception.TalentNotFoundException;
import com.mangalaxy.mango.repository.EmployerRepository;
import com.mangalaxy.mango.repository.TalentRepository;
import com.mangalaxy.mango.service.EmployerRelationshipService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EmployerRelationshipServiceImpl implements EmployerRelationshipService {
  private final EmployerRepository employerRepository;
  private final TalentRepository talentRepository;
  private final ModelMapper modelMapper;

  @Transactional
  @Override
  public boolean toggleTalentBookmark(Long employerId, Long talentId, boolean bookmarked) {
    boolean alreadyBookmarked = employerRepository.existsByIdAndBookmarkedTalents_Id(employerId, talentId);
    if (alreadyBookmarked && bookmarked) {
      return true;
    } else if(!alreadyBookmarked && !bookmarked) {
      return false;
    } else {
      Employer employer = employerRepository.findById(employerId).orElseThrow(EmployerNotFoundException::new);
      Talent talent = talentRepository.findById(talentId).orElseThrow(TalentNotFoundException::new);
      if (bookmarked) {
        employer.addTalentToBookmarkedTalents(talent);
      } else {
        employer.removeTalentFromBookmarkedTalents(talent);
      }
      Employer updatedEmployer = employerRepository.save(employer);
      return updatedEmployer.getBookmarkedTalents().contains(talent);
    }
  }

  @Transactional(readOnly = true)
  @Override
  public Page<TalentResponse> fetchBookmarkedTalents(Long employerId, Pageable pageable) {
    Page<Talent> talents = talentRepository.findAllByPotentialEmployers_Id(employerId, pageable);
    return talents.map(talent -> modelMapper.map(talent, TalentResponse.class));
  }


}
