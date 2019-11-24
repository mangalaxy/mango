package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Short> {

}
