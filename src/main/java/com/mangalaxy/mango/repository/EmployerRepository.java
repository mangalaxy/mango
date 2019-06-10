package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.model.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer, Long> {

  Optional<Employer> findByWorkEmail(@NotNull String email);

}
