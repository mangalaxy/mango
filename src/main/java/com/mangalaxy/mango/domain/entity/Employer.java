package com.mangalaxy.mango.domain.entity;

import com.mangalaxy.mango.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(
      callSuper = true,
      doNotUseGetters = true,
      onlyExplicitlyIncluded = true
)
@ToString(callSuper = true, doNotUseGetters = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NaturalIdCache
@Table(name = "employer")
public class Employer extends DateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employer_sequence")
  @SequenceGenerator(
        name = "employer_sequence",
        sequenceName = "employer_id_seq",
        allocationSize = 1
  )
  @EqualsAndHashCode.Include
  @Column(name = "id", nullable = false, unique = true, updatable = false)
  private Long id;

  @EqualsAndHashCode.Include
  @Column(name = "full_name", nullable = false)
  private String fullName;

  @EqualsAndHashCode.Include
  @NaturalId
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @ToString.Exclude
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "phone")
  private String phone;

  @Column(name = "job_title")
  private String jobTitle;

  @Column(name = "photo_url")
  private String photoUrl;

  @Enumerated(EnumType.STRING)
  @NaturalId
  @Column(name = "role", nullable = false)
  private Role role;

  @ToString.Exclude
  @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = { CascadeType.PERSIST, CascadeType.MERGE },
        optional = false
  )
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "location_id", nullable = false)
  private Location location;

  @ToString.Exclude
  @OneToMany(
        mappedBy = "publisher",
        cascade = CascadeType.ALL,
        orphanRemoval = true
  )
  private Set<Job> jobs = new HashSet<>();

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinTable(name = "bookmarked_talents",
        joinColumns = @JoinColumn(name = "employer_id"),
        inverseJoinColumns = @JoinColumn(name = "talent_id"))
  private Set<Talent> bookmarkedTalents = new HashSet<>();

  /**
   * Add a new job to existing set of open jobs.
   * @param job a job to add.
   */
  public void addJob(Job job) {
    jobs.add(job);
    job.setPublisher(this);
  }

  /**
   * Removes the job from set of open jobs.
   * @param job a job to remove.
   */
  public void removeJob(Job job) {
    jobs.remove(job);
    job.setPublisher(null);
  }

  public void addTalentToBookmarkedTalents(Talent talent) {
    bookmarkedTalents.add(talent);
    talent.getPotentialEmployers().add(this);
  }

  public void removeTalentFromBookmarkedTalents(Talent talent) {
    bookmarkedTalents.remove(talent);
    talent.getPotentialEmployers().remove(this);
  }
}

