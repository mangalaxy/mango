package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

  Page<Job> findAllByPublisher_Id(Long id, Pageable pageable);

  Job findByIdAndPublisher_Id(Long jobId, Long employerId);
}
