package com.mangalaxy.mango.domain.dto.response;

import com.mangalaxy.mango.domain.entity.Company;
import com.mangalaxy.mango.domain.entity.Job;
import com.mangalaxy.mango.domain.entity.Profile;
import lombok.Data;

import java.util.Set;

@Data
public class SkillResponse {
  private Long id;
  private String name;
}
