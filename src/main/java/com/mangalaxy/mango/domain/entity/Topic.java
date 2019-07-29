package com.mangalaxy.mango.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "topic")
public class Topic extends AbstractPersistable<Short> {

  private String name;

  @ElementCollection
  @Column(name = "tag")
  private List<String> tags;

  @OneToMany(mappedBy = "topic",
        cascade = { CascadeType.PERSIST, CascadeType.MERGE },
        orphanRemoval = true
  )
  @EqualsAndHashCode.Exclude
  private Set<Post> posts = new HashSet<>();

}

