package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.EmployerRequest;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.domain.entity.Talent;
import com.mangalaxy.mango.repository.EmployerRepository;
import com.mangalaxy.mango.repository.LocationRepository;
import com.mangalaxy.mango.repository.TalentRepository;
import com.mangalaxy.mango.util.EmployerNotFoundExeption;
import com.mangalaxy.mango.util.TalentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Service
@Transactional
public class EmployerServiceImpl implements EmployerService {

  private final EmployerRepository employerRepository;
  private final ModelMapper modelMapper;
  private final LocationRepository locationRepository;
  private final TalentRepository talentRepository;

  @Override
  public Page<EmployerResponse> getEmployersByParams(Pageable pageable) {
    Page<Employer> employersByparams = employerRepository.findAll(pageable);
    return employersByparams.map(employer -> modelMapper.map(employer, EmployerResponse.class));
  }

  @Override
  public EmployerResponse getEmployerById(Long id) {
    Employer employer = employerRepository.findById(id).orElseThrow(EmployerNotFoundExeption::new);
    return modelMapper.map(employer, EmployerResponse.class);
  }

  @Override
  public EmployerResponse createNewEmployer(EmployerRequest employerRequest) {
    Employer employer = modelMapper.map(employerRequest, Employer.class);
    Employer savedEmployer = employerRepository.save(employer);
    return modelMapper.map(savedEmployer, EmployerResponse.class);
  }

  @Override
  public EmployerResponse updateEmployer(EmployerRequest employerRequest, Long id) {
    Employer employerFromDataBase = employerRepository.findById(id).orElseThrow(EmployerNotFoundExeption::new);
    Employer employer = modelMapper.map(employerRequest, Employer.class);
    employer.setId(employerFromDataBase.getId());
    Employer updatedEmployer = employerRepository.save(employer);
    return modelMapper.map(updatedEmployer, EmployerResponse.class);
  }

  @Override
  public void deleteEmployer(Long id) {
    Employer employer = employerRepository.findById(id).orElseThrow(EmployerNotFoundExeption::new);
    Location location = locationRepository.findById(employer.getLocation().getId()).orElse(null);
    Set<Employer> employers = location.getEmployers();
    employers.remove(employer);
    location.setEmployers(employers);
    locationRepository.save(location);
    employerRepository.delete(employer);
  }

  @Override
  public EmployerResponse matchTalentToEmployer(Long employerId, Long talentId, boolean set) {
    Employer employer = employerRepository.findById(employerId).orElseThrow(EmployerNotFoundExeption::new);
    Talent talent = talentRepository.findById(talentId).orElseThrow(TalentNotFoundException::new);
    Set<Talent> talents = employer.getMatchedTalents();
    Set<Employer> employers = talent.getMatchedEmployers();

    if (set) {
      talents.add(talent);
    } else {
      talents.remove(talent);
      employers.remove(employer);
    }

    employer.setMatchedTalents(talents);
    talent.setMatchedEmployers(employers);
    talentRepository.save(talent);
    Employer updatedEmployer = employerRepository.save(employer);
    return modelMapper.map(updatedEmployer, EmployerResponse.class);
  }

  @Override
  public Page<TalentResponse> getMatchedTalentsForEmployerJob(Long employerId, Long jobId, Pageable pageable) {
    //Here will be method for get suitable talents for certain job
    return null;
  }
}
