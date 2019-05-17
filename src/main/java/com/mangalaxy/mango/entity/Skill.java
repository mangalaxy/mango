package com.mangalaxy.mango.entity;

import lombok.Data;

/**
 * Represents specified skill related to job role.
 */
@Data
public class Skill {

  private Long id;
  private String name;
  // TODO: Change on JobRole enumeration
  private String jobRole;
}
