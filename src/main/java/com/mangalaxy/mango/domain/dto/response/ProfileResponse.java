package com.mangalaxy.mango.domain.dto.response;

import com.mangalaxy.mango.domain.CandidateStatus;
import com.mangalaxy.mango.domain.dto.request.LocationRequest;
import com.mangalaxy.mango.domain.entity.Education;
import com.mangalaxy.mango.domain.entity.Experience;
import com.mangalaxy.mango.domain.entity.Language;
import com.mangalaxy.mango.domain.entity.Salary;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProfileResponse {
  private Long id;
  private TalentResponse owner;
  private String photoUrl;
  private String selectedJobRole;
  private Set<SkillResponse> skills;
  private CandidateStatus status;
  private LocationRequest preferredLocation;
  private Salary preferredSalary;
  private Set<String> expectations;
  private List<String> preferredCompanyType;
  private List<Experience> experiences;
  private List<Education> educations;
  private List<Language> preferredLang;
}
