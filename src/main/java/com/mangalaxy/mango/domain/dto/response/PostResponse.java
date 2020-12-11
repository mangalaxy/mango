package com.mangalaxy.mango.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponse {
  Long id;
  String topicName;
  String headline;
  String opening;
  String author;
  String imageUrl;
  String content;
  Integer viewsCount;
  Integer likesCount;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  LocalDateTime createdDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  LocalDateTime modifiedDate;
}
