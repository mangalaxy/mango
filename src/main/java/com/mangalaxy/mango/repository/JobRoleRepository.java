package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.JobRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRoleRepository extends CrudRepository<JobRole, Short> {

  JobRole findByTitle(String title);

}
