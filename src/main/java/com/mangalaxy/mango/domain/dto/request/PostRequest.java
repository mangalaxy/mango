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

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(doNotUseGetters = true)
public class PostRequest {

  @NotBlank
  @Size(max = 60)
  String headline;

  @NotBlank
  @Size(max = 255)
  String opening;

  @URL
  @Size(max = 255)
  String imageUrl;

  @NotBlank
  @Size(max = 60)
  String author;

  @NotBlank
  @Size(max = 5000)
  String content;

  @JsonCreator
  public PostRequest of(
        @JsonProperty("headline") String headline,
        @JsonProperty("opening") String opening,
        @JsonProperty("imageUrl") String imageUrl,
        @JsonProperty("author") String author,
        @JsonProperty("content") String content) {
    return new PostRequest(
          headline,
          opening,
          imageUrl,
          author,
          content
    );
  }
}
