package com.mangalaxy.mango.repository;

import com.mangalaxy.mango.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
  @Override
  <S extends Location> S save(S s);

  @Override
  List<Location> findAll();

  @Override
  Optional<Location> findById(Long aLong);

  @Override
  boolean existsById(Long aLong);

  @Override
  void deleteById(Long aLong);
}
