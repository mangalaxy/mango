package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileRequest {
  private final Long id;
  private final String photoUrl;
  private final String selectedJobRole;

  @JsonCreator
  public ProfileRequest(@JsonProperty("id") Long id, @JsonProperty("photoUrl") String photoUrl,
                        @JsonProperty("selectedJobRole") String selectedJobRole) {
    this.id = id;
    this.photoUrl = photoUrl;
    this.selectedJobRole = selectedJobRole;
  }
}
