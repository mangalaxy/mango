package com.mangalaxy.mango.config.mapping;

import com.github.jmnarloch.spring.boot.modelmapper.ModelMapperConfigurer;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.springframework.stereotype.Component;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Component
public class ModelMapperConfig implements ModelMapperConfigurer {

  @Override
  public void configure(ModelMapper modelMapper) {
    modelMapper.getConfiguration()
          .setSourceNamingConvention(NamingConventions.NONE)
          .setDestinationNamingConvention(NamingConventions.NONE)
          .setMatchingStrategy(MatchingStrategies.STRICT)
          .setFieldMatchingEnabled(true)
          .setSkipNullEnabled(true)
          .setFieldAccessLevel(PRIVATE);
  }
}
