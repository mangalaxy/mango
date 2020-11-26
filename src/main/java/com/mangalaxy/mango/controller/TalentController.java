package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.TalentUpdateRequest;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.security.CurrentUser;
import com.mangalaxy.mango.security.UserPrincipal;
import com.mangalaxy.mango.service.TalentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class TalentController {
  private final TalentService talentService;

  @PreAuthorize("hasRole('TALENT')")
  @GetMapping(value = "/api/v1/talents/{talentId}")
  public ResponseEntity<TalentResponse> getSpecifiedTalent(@PathVariable Long talentId) {
    TalentResponse talent = talentService.fetchTalentById(talentId);
    return ResponseEntity.ok(talent);
  }

  @PreAuthorize("hasRole('TALENT')")
  @GetMapping("/api/v1/talents/me")
  public ResponseEntity<TalentResponse> getCurrentTalent(@CurrentUser UserPrincipal principal) {
    Long employerId = principal.getId();
    URI redirectUri = MvcUriComponentsBuilder.fromMethodName(this.getClass(), "getSpecifiedTalent", employerId)
          .build()
          .toUri();
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.setLocation(redirectUri);
    return new ResponseEntity<>(responseHeaders, HttpStatus.FOUND);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/api/v1/talents")
  public ResponseEntity<Page<TalentResponse>> getPaginatedTalents(Pageable pageable) {
    Page<TalentResponse> talents = talentService.fetchTalentPage(pageable);
    return ResponseEntity.ok(talents);
  }

  @PreAuthorize("hasRole('TALENT')")
  @PutMapping("/api/v1/talents/{talentId}")
  public ResponseEntity<TalentResponse> updateSpecifiedTalent(@PathVariable("talentId") Long id,
                                                              @Validated @RequestBody TalentUpdateRequest talentRequest) {
    TalentResponse response = talentService.updateTalentById(id, talentRequest);
    return ResponseEntity.ok(response);
  }

  @PreAuthorize("hasRole('TALENT')")
  @DeleteMapping("/api/v1/talents/{talentId}")
  public ResponseEntity<Void> deleteSpecifiedTalent(@PathVariable("talentId") Long id) {
    talentService.deleteTalentById(id);
    return ResponseEntity.noContent().build();
  }

}

