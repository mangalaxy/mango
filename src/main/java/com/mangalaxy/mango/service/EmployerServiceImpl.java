package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.request.EmployerRequest;
import com.mangalaxy.mango.domain.dto.response.EmployerResponse;
import com.mangalaxy.mango.domain.entity.Employer;
import com.mangalaxy.mango.domain.entity.Location;
import com.mangalaxy.mango.repository.EmployerRepository;
import com.mangalaxy.mango.repository.LocationRepository;
import com.mangalaxy.mango.util.EmployerNotFoundExeption;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployerServiceImpl implements EmployerService {
  private final EmployerRepository employerRepository;
  private final ModelMapper modelMapper;
  private final LocationRepository locationRepository;

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
    Employer employer = modelMapper.map(employerRequest, Employer.class);
    employer.setId(id);
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
}
