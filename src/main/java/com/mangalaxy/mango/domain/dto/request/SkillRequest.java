package com.mangalaxy.mango.domain.dto.request;

import com.mangalaxy.mango.domain.entity.Company;
import com.mangalaxy.mango.domain.entity.Job;
import com.mangalaxy.mango.domain.entity.Profile;
import lombok.Data;

import java.util.Set;

@Data
public class SkillRequest {
  private Long id;
  private String name;
  private Set<Job> jobs;
  private Set<Company> companies;
  private Set<Profile> profiles;
}
