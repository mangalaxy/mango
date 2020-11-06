package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.domain.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Short> {
  Location findFirstByCity(String city);
  List<Location> findAllByCountryIgnoreCase(String country);
}
