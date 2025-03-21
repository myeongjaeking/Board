package com.example.board.global.config.jwt;

import static com.example.board.global.exception.GlobalExceptionCode.NO_AUTHENTICATED;

import com.example.board.global.exception.CustomException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {

  public static String getUserEmail() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new CustomException(NO_AUTHENTICATED);
    }

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    return userDetails.getUsername();
  }

}