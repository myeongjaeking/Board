package com.example.board.global.config.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {

  public static String getUserEmail() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new RuntimeException("No user is currently authenticated");
    }
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    return userDetails.getUsername();
  }

}