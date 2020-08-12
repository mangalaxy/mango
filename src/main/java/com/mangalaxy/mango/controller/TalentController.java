package com.mangalaxy.mango.controller;

import com.mangalaxy.mango.domain.dto.request.TalentRequest;
import com.mangalaxy.mango.domain.dto.response.TalentResponse;
import com.mangalaxy.mango.service.TalentService;
import com.mangalaxy.mango.util.ResourceNotFoundException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/talents")
@RequiredArgsConstructor
public class TalentController {

  private final TalentService talentService;

  @GetMapping("/{talentId}")
  public ResponseEntity<TalentResponse> getTalentById(@PathVariable Long talentId)
        throws ResourceNotFoundException {
    TalentResponse talent = talentService.getTalentById(talentId);
    return ResponseEntity.ok(talent);
  }

  @GetMapping
  public ResponseEntity<Page<TalentResponse>> getAllTalents(Pageable pageable) throws ResourceNotFoundException {
    Page<TalentResponse> talents = talentService.findAll(pageable);
    return ResponseEntity.ok(talents);
  }

  @PostMapping
  public ResponseEntity<TalentResponse> createTalent(@RequestBody TalentRequest talentRequest) {
    TalentResponse talent = talentService.createNewTalent(talentRequest);
    return new ResponseEntity<>(talent, HttpStatus.CREATED);
  }

  @PreAuthorize("hasAuthority('TALENT')")
  @PutMapping("/talents/{talentId}")
  public ResponseEntity<TalentResponse> updateTalent(@PathVariable Long talentId,
                                                     @RequestBody TalentRequest talentRequest) {
    TalentResponse response = talentService.updateTalent(talentRequest, talentId);
    return ResponseEntity.ok(response);
  }

  @PreAuthorize("hasAuthority('TALENT')")
  @DeleteMapping("/talents/{talentId}")
  public ResponseEntity<Void> deleteTalent(@PathVariable Long talentId) {
    talentService.deleteTalent(talentId);
    return ResponseEntity.noContent().build();
  }

  @PreAuthorize("hasAuthority('TALENT')")
  @GetMapping("/talents/me")
  @ApiOperation(value = "Get authenticated talent")
  public ResponseEntity<TalentResponse> getCurrentTalent() {
    TalentResponse talentResponse = talentService.getCurrentTalent();
    return ResponseEntity.ok(talentResponse);
  }

  @PreAuthorize("hasAuthority('TALENT')")
  @PutMapping("/talents/me")
  public ResponseEntity<TalentResponse> updateCurrentTalent(@RequestBody TalentRequest talentRequest) {
    TalentResponse talentResponse = talentService.updateCurrentTalent(talentRequest);
    return ResponseEntity.ok(talentResponse);
  }

  @PreAuthorize("hasAuthority('TALENT')")
  @DeleteMapping("/talents/me")
  public ResponseEntity<Void> deleteCurrentTalent() {
    talentService.deleteCurrentTalent();
    return ResponseEntity.noContent().build();
  }
}

