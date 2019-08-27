package com.mangalaxy.mango.domain.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileRequest {
  private final Long id;
  private final String photoUrl;
  private final String selectedJobRole;
}
