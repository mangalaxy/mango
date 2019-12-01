package com.mangalaxy.mango.domain.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true, onlyExplicitlyIncluded = true)
@ToString(callSuper = true, doNotUseGetters = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NaturalIdCache
@Table(name = "employer")
public class Employer extends AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employerSeq")
  @SequenceGenerator(name = "employerSeq", sequenceName = "employer_id_seq")
  @Column(name = "id", nullable = false, unique = true, updatable = false)
  private Long id;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @NaturalId
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "phone")
  private String phoneNumber;

  @Column(name = "job_title")
  private String jobTitle;

  @Column(name = "photo_url")
  private String photo;

  @EqualsAndHashCode.Exclude
  @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE },
        fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;

  @EqualsAndHashCode.Exclude
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "location_id", nullable = false)
  private Location location;

  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "publisher",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
  private Set<Job> jobs = new HashSet<>();

  @EqualsAndHashCode.Exclude
  @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinTable(name = "bookmarked_talents",
        joinColumns = @JoinColumn(name = "employer_id"),
        inverseJoinColumns = @JoinColumn(name = "talent_id"))
  private Set<Talent> bookmarkedTalents = new HashSet<>();

  /**
   * Add a new job to existing set of open jobs.
   * @param job a job to add.
   */
  public void addJob(final Job job) {
    jobs.add(job);
    job.setPublisher(this);
  }

  /**
   * Removes the job from set of open jobs.
   * @param job a job to remove.
   */
  public void removeJob(final Job job) {
    jobs.remove(job);
    job.setPublisher(null);
  }

}

