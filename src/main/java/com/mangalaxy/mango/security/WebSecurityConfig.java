package com.mangalaxy.mango.security;

import com.mangalaxy.mango.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private EmployerService employerService;

  @Autowired
  public WebSecurityConfig(EmployerService employerService) {
    this.employerService = employerService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
          .antMatchers("/login").permitAll()
          .anyRequest().authenticated()
        .and()
          .formLogin()
          .permitAll()
        .and()
          .logout()
          .permitAll();

  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    auth.userDetailsService(employerService).passwordEncoder(new BCryptPasswordEncoder());
  }
}
