package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TalentRepository extends JpaRepository<Talent, Long> {

  Optional<Talent> findByEmail(String email);

}
