package com.mangalaxy.mango.security;

import com.mangalaxy.mango.security.jwt.JwtAuthenticationEntryPoint;
import com.mangalaxy.mango.security.jwt.JwtAuthenticationFilter;
import com.mangalaxy.mango.security.jwt.JwtTokenProvider;
import com.mangalaxy.mango.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomUserDetailsService userDetailsService;
  private final JwtAuthenticationEntryPoint unauthorizedHandler;
  private final JwtTokenProvider jwtTokenProvider;

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService);
  }

  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring()
        .antMatchers("/favicon.ico",
            "/**/*.png",
            "/**/*.gif",
            "/**/*.svg",
            "/**/*.jpg",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors().and().csrf().disable()
        .exceptionHandling()
          .authenticationEntryPoint(unauthorizedHandler)
        .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
          .antMatchers("/api/v1/auth/**").permitAll()
        .anyRequest()
        .authenticated()
        .and()
          .formLogin()
          .loginPage("/")
          .permitAll()
        .and()
          .logout()
          .logoutUrl("/api/v1/logout")
          .permitAll();

    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

  }

}
