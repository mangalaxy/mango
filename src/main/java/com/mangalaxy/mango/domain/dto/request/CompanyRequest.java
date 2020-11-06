package com.mangalaxy.mango.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.Value;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(doNotUseGetters = true)
public class CompanyRequest {

  @NotBlank
  @Size(min = 3, max = 45)
  String name;

  @Size(max = 255)
  String headline;

  @URL
  @Size(max = 255)
  String logoUrl;

  @Size(max = 255)
  String headquarterAddress;

  @Size(max = 45)
  String size;

  @Size(max = 100)
  String industry;

  @URL
  @Size(max = 255)
  String promoUrl;

  String about;
  Set<SkillRequest> skills;
  Set<String> perks;
  Set<String> benefits;
  Set<String> links;
  Set<String> photos;

  @JsonCreator
  public CompanyRequest of(
        @JsonProperty("name") String name,
        @JsonProperty("headline") String headline,
        @JsonProperty("logoUrl") String logoUrl,
        @JsonProperty("headquarterAddress") String headquarterAddress,
        @JsonProperty("size") String size,
        @JsonProperty("industry") String industry,
        @JsonProperty("promoUrl") String promoUrl,
        @JsonProperty("about") String about,
        @JsonProperty("skills") Set<SkillRequest> skills,
        @JsonProperty("perks") Set<String> perks,
        @JsonProperty("benefits") Set<String> benefits,
        @JsonProperty("links") Set<String> links,
        @JsonProperty("photos") Set<String> photos) {
    return new CompanyRequest(
          name,
          headline,
          logoUrl,
          headquarterAddress,
          size,
          industry,
          promoUrl,
          about,
          skills,
          perks,
          benefits,
          links,
          photos
    );
  }
}
