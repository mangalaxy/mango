package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.TalentRequest;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.service.TalentService;
import com.mangalaxy.mango.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class TalentController {
  private final TalentService talentService;

  @GetMapping(value = "/api/v1/talents/{talentId}")
  public ResponseEntity<TalentResponse> getTalentById(@PathVariable Long talentId) {
    TalentResponse talent = talentService.getTalentById(talentId);
    return ResponseEntity.ok(talent);
  }

  @GetMapping("/api/v1/talents")
  public ResponseEntity<Page<TalentResponse>> getAllTalents(Pageable pageable)
        throws ResourceNotFoundException {
    Page<TalentResponse> talents = talentService.findAll(pageable);
    return ResponseEntity.ok(talents);
  }

  @PostMapping("/api/v1/talents")
  public ResponseEntity<TalentResponse> createTalent(@RequestBody TalentRequest talentRequest) {
    TalentResponse talent = talentService.createNewTalent(talentRequest);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
          .path("/{id}")
          .buildAndExpand(talent.getId())
          .toUri();
    return ResponseEntity.created(location).build();
  }

  @PreAuthorize("hasAuthority('TALENT')")
  @PutMapping("/api/v1/talents/{talentId}")
  public ResponseEntity<TalentResponse> updateTalent(@PathVariable Long talentId,
                                                     @RequestBody TalentRequest talentRequest) {
    TalentResponse response = talentService.updateTalent(talentRequest, talentId);
    return ResponseEntity.ok(response);
  }

  @PreAuthorize("hasAuthority('TALENT')")
  @DeleteMapping("/api/v1/talents/{talentId}")
  public ResponseEntity<Void> deleteTalent(@PathVariable Long talentId) {
    talentService.deleteTalent(talentId);
    return ResponseEntity.noContent().build();
  }

  @PreAuthorize("hasAuthority('TALENT')")
  @GetMapping("/api/v1/talents/me")
  public ResponseEntity<TalentResponse> getCurrentTalent() {
    TalentResponse talentResponse = talentService.getCurrentTalent();
    return ResponseEntity.ok(talentResponse);
  }

  @PreAuthorize("hasAuthority('TALENT')")
  @PutMapping("/api/v1/talents/me")
  public ResponseEntity<TalentResponse> updateCurrentTalent(@RequestBody TalentRequest talentRequest) {
    TalentResponse talentResponse = talentService.updateCurrentTalent(talentRequest);
    return ResponseEntity.ok(talentResponse);
  }

  // TODO: This is a highly controversial and dangerous endpoint,
  //  it needs to be reviewed in the REST API and how it should be
  //  used by the client side.
  //  If it turns out that the client side is not using it, then delete it.
  @PreAuthorize("hasAuthority('TALENT')")
  @DeleteMapping("/api/v1/talents/me")
  public ResponseEntity<Void> deleteCurrentTalent() {
    talentService.deleteCurrentTalent();
    return ResponseEntity.noContent().build();
  }
}

