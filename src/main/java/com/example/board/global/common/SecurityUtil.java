package com.example.board.global.common;

import static com.example.board.global.exception.GlobalExceptionCode.NO_AUTHENTICATED;

import com.example.board.global.exception.CustomException;
import com.example.board.member.entity.Member;
import com.example.board.member.repository.MemberRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

  private static MemberRepository memberRepository;

  public SecurityUtil(MemberRepository memberRepository) {
    SecurityUtil.memberRepository = memberRepository;
  }

  public static Member getMember() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new CustomException(NO_AUTHENTICATED);
    }

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    return memberRepository.findByEmail(userDetails.getUsername());
  }

}