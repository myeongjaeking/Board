package com.example.board.global.config.jwt.service;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
public class WebOAuthSecurityConfig {

  private final TokenProvider tokenProvider;

  private static final String[] ALLOWED_URLS = {
      "/api/token", "/h2-console", "/swagger", "/swagger-ui.html", "/swagger-ui/**", "/api-docs",
      "/api-docs/**", "/v3/api-docs/**","/api/auth/**"
  };

  @Bean
  public WebSecurityCustomizer configure() {
    return (web) -> web.ignoring()
        .requestMatchers(toH2Console())
        .requestMatchers("/img/**", "/css/**", "/js/**");
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .logout(AbstractHttpConfigurer::disable)
        .sessionManagement(management -> management.sessionCreationPolicy(
            SessionCreationPolicy.STATELESS));
    http
        .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(ALLOWED_URLS).permitAll()
            .anyRequest().authenticated());
    return http.build();
  }

  @Bean
  public TokenAuthenticationFilter tokenAuthenticationFilter() {
    return new TokenAuthenticationFilter(tokenProvider);
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

}