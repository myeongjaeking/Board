package com.example.board.global.common;

import static com.example.board.global.exception.GlobalExceptionCode.NO_AUTHENTICATED;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

  public static String getNickname() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      throw NO_AUTHENTICATED.newException();
    }

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    return userDetails.getUsername();
  }

}