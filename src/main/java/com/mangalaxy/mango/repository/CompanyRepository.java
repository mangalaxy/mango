package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
  Optional<Company> findByNameIgnoreCase(String name);
}
