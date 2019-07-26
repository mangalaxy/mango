package com.mangalaxy.mango.facade;

import com.mangalaxy.mango.model.entity.BaseEntity;
import com.mangalaxy.mango.service.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
@SuppressWarnings("unchecked")
public abstract class AbstractDtoFacade <E extends BaseEntity, I, O> {

  @Autowired
  @SuppressWarnings("ALL")
  protected CrudService<E> crudService;

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  public O getById(Long id) {
    return mapEntityToResponseDto(crudService.getByid(id));
  }

  public List<O> getAll() {
    return mapListOfEntitiesToResponseDtoList(crudService.getAll());
  }

  public O create(I request) {
    E entity = crudService.create(mapRequestDtoToEntity(request));
    return mapEntityToResponseDto(entity);
  }

  public O update(I request, Long id) {
    E entity = crudService.update(mapRequestDtoToEntity(request), id);
    return mapEntityToResponseDto(entity);
  }

  public O delete(Long id) {
    return mapEntityToResponseDto(crudService.delete(id));
  }

  public O mapEntityToResponseDto(E entity) {
    return entity != null ? modelMapper().map(entity, (Class<O>) ((ParameterizedType) getClass()
        .getGenericSuperclass()).getActualTypeArguments()[2]) : null;
  }

  public E mapRequestDtoToEntity(I request) {
    return request != null ? modelMapper().map(request, (Class<E>) ((ParameterizedType) getClass()
        .getGenericSuperclass()).getActualTypeArguments()[0]) : null;
  }

  public List<O> mapListOfEntitiesToResponseDtoList(List<E> entities) {
    return entities.stream().map(entity -> mapEntityToResponseDto(entity)).collect(Collectors.toList());
  }

  public Page<O> mapListOfEntitiesToResponseDtoList(Page<E> entities) {
    return entities.map(entity -> mapEntityToResponseDto(entity));
  }
}
