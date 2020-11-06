package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
  Page<Job> findAllByPublisher_Id(Long id, Pageable pageable);
  Optional<Job> findByIdAndPublisher_Id(Long jobId, Long employerId);
  void deleteByIdAndPublisher_Id(Long jobId, Long employerId);
  boolean existsByIdAndPublisher_Id(Long jobId, Long employerId);
}
