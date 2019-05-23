package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.model.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
}
