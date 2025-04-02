package com.example.board.auth.service;

import static com.example.board.global.exception.GlobalExceptionCode.NOT_VALID_REFRESH_TOKEN;

import com.example.board.auth.dto.request.CreateAccessTokenRequest;
import com.example.board.global.common.SecurityUtil;
import com.example.board.global.security.jwt.TokenProvider;
import com.example.board.global.exception.CustomException;
import com.example.board.member.entity.Member;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {

  private final TokenProvider tokenProvider;

  public String createNewAccessToken(CreateAccessTokenRequest createAccessTokenRequest) {
    if (!tokenProvider.validToken(createAccessTokenRequest.getRefreshToken())) {
      throw NOT_VALID_REFRESH_TOKEN.newException();
    }
    Member member = SecurityUtil.getMember();

    return tokenProvider.generateToken(member, Duration.ofHours(2));
  }

}