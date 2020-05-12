package com.mangalaxy.mango.config;

import com.github.jmnarloch.spring.boot.modelmapper.ModelMapperConfigurer;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConfig implements ModelMapperConfigurer {

  @Override
  public void configure(ModelMapper modelMapper) {
    modelMapper.getConfiguration()
          .setSourceNamingConvention(NamingConventions.NONE)
          .setDestinationNamingConvention(NamingConventions.NONE);
  }
}
