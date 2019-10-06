package com.mangalaxy.mango.domain.dto.request;

import com.mangalaxy.mango.domain.entity.Company;
import com.mangalaxy.mango.domain.entity.Job;
import com.mangalaxy.mango.domain.entity.Profile;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class SkillRequest {
  private final Long id;
  private final String name;
}
