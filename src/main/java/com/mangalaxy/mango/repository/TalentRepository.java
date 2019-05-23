package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.model.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TalentRepository extends JpaRepository<Talent, Long> {

  Optional<Talent> findByEmail(String email);

}
