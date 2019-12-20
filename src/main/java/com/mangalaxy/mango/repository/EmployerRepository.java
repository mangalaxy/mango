package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

  Optional<Employer> findByEmail(@NotNull String email);

}
