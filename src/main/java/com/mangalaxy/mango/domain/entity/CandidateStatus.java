package com.mangalaxy.mango.domain.entity;


/**
 * Determines candidate status with respect to employers.
 *
 * @author Yuri Podolsky
 */
public enum CandidateStatus {
  SEARCHING("I'm looking for a new job"),
  OFFERING("Open for new job offers"),
  EMPLOYED("Currently I'm not available for hiring");

  private String definition;

  CandidateStatus(String definition) {
    this.definition = definition;
  }

  public String getDefinition() {
    return definition;
  }

}
