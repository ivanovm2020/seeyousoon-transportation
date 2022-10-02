package ru.ivanov.transportation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web
                        .configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation
             .authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web
             .builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
        .antMatchers("/fill", "/orders").access("hasRole('USER')")
        .antMatchers("/", "/**").access("permitAll")

      .and()
        .formLogin()
          .loginPage("/login")

      .and()
        .logout()
          .logoutSuccessUrl("/")

      // Make H2-Console non-secured; for debug purposes
      .and()
        .csrf()
          .ignoringAntMatchers("/h2-console/**")

      // Allow pages to be loaded in frames from the same origin; needed for H2-Console
      .and()
        .headers()
          .frameOptions()
            .sameOrigin()
      ;
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {

    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(encoder());
  }
}
