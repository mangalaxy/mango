package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Short> {

  Location findByCity(String city);

  Page<Location> findAll(Pageable pageable);
}
