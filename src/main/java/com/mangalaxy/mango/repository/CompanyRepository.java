package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
