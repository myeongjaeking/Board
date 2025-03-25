package com.example.board.auth.service;

import static com.example.board.global.exception.GlobalExceptionCode.NOT_VALID_REFRESH_TOKEN;

import com.example.board.global.common.SecurityUtil;
import com.example.board.global.security.jwt.TokenProvider;
import com.example.board.global.exception.CustomException;
import com.example.board.member.entity.Member;
import com.example.board.member.service.MemberService;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {

  private final TokenProvider tokenProvider;

  //CutomException
  public String createNewAccessToken(String refreshToken) {
    if (!tokenProvider.validToken(refreshToken)) {
      throw new CustomException(NOT_VALID_REFRESH_TOKEN);
    }

    Member member = SecurityUtil.getMember();

    return tokenProvider.generateToken(member, Duration.ofHours(2));
  }

}