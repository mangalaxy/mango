package com.mangalaxy.mango.service;

import com.mangalaxy.mango.domain.dto.response.JobResponse;
import com.mangalaxy.mango.domain.entity.Job;
import com.mangalaxy.mango.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

  private final JobRepository jobRepository;
  private final ModelMapper modelMapper;

  @Override
  public Page<JobResponse> getJobsByParameters(String jobRole, String city, Pageable pageable) {
    Page<Job> allJobs = jobRepository.findAll(pageable);
    List<JobResponse> jobsByParems = allJobs.getContent().stream()
        .filter(job -> jobRole != null ? job.getJobRole().toLowerCase().equals(jobRole.toLowerCase()) : true)
        .filter(job -> city != null ? job.getLocation().getCity().toLowerCase().equals(city.toLowerCase()) : true)
        .map(job -> modelMapper.map(job, JobResponse.class))
        .collect(Collectors.toList());
    return new PageImpl<>(jobsByParems, pageable, jobsByParems.size());
  }
}
