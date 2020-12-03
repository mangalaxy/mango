package com.mangalaxy.mango.security;

import com.mangalaxy.mango.security.jwt.JwtAuthenticationEntryPoint;
import com.mangalaxy.mango.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(
      prePostEnabled = true,
      securedEnabled = true,
      jsr250Enabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private final UserDetailsService customUserDetailsService;
  private final JwtAuthenticationEntryPoint unauthorizedHandler;

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/v2/api-docs",
          "/configuration/ui",
          "/swagger-resources/**",
          "/configuration/security",
          "/swagger-ui.html",
          "/webjars/**"
    );
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http
          .cors()
            .and()
          .csrf()
            .disable()
          .httpBasic().disable()
          .exceptionHandling()
            .authenticationEntryPoint(unauthorizedHandler)
            .and()
          .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
          .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
          .authorizeRequests()
            .antMatchers("/api/v1/auth/**").permitAll()
          .antMatchers(HttpMethod.GET, "/api/v1/jobs", "/api/v1/posts/**", "/api/v1/locations/**")
            .permitAll()
          .anyRequest()
            .authenticated();
    // @formatter:on

  }

}
