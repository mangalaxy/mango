package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Talent;
import com.mangalaxy.mango.repository.EmployerRepository;
import com.mangalaxy.mango.repository.TalentRepository;
import com.mangalaxy.mango.util.EmployerNotFoundException;
import com.mangalaxy.mango.util.TalentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class EmployerRelationshipServiceImpl implements EmployerRelationshipService {

  private final EmployerRepository employerRepository;
  private final TalentRepository talentRepository;
  private final ModelMapper modelMapper;

  @Override
  public EmployerResponse matchTalentToEmployer(Long employerId, Long talentId, boolean set) {
    Employer employer = findEmployer(employerId);
    Talent talent = talentRepository.findById(talentId).orElseThrow(TalentNotFoundException::new);
    Set<Talent> talents = employer.getBookmarkedTalents();
    Set<Employer> employers = talent.getPotentialEmployers();

    if (set) {
      talents.add(talent);
    } else {
      talents.remove(talent);
      employers.remove(employer);
    }

    employer.setBookmarkedTalents(talents);
    talent.setPotentialEmployers(employers);
    talentRepository.save(talent);
    Employer updatedEmployer = employerRepository.save(employer);
    return modelMapper.map(updatedEmployer, EmployerResponse.class);
  }

  @Override
  public Page<TalentResponse> getMatchedTalentsForEmployerJob(Long employerId, Long jobId, Pageable pageable) {
    //Here will be method for get suitable talents for certain job
    return null;
  }

  // Shortcut method to find employer
  private Employer findEmployer(Long id) {
    return employerRepository.findById(id).orElseThrow(EmployerNotFoundException::new);
  }

}
