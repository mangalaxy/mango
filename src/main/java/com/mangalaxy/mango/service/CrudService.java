package com.mangalaxy.mango.service;

import com.mangalaxy.mango.model.entity.BaseEntity;

import java.util.List;

public interface CrudService <E extends BaseEntity> {

  E getByid(Long id);

  List<E> getAll();

  E create(E entity);

  E update (E entity, Long id);

  E delete(Long id);
}
