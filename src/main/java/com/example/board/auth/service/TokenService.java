package com.example.board.auth.service;

import static com.example.board.global.exception.GlobalExceptionCode.NOT_VALID_REFRESH_TOKEN;

import com.example.board.auth.dto.request.CreateAccessTokenRequest;
import com.example.board.global.common.SecurityUtil;
import com.example.board.global.security.jwt.TokenProvider;
import com.example.board.member.entity.Member;
import com.example.board.member.repository.MemberRepository;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {

  private final TokenProvider tokenProvider;
  private final MemberRepository memberRepository;

  public String createNewAccessToken(CreateAccessTokenRequest createAccessTokenRequest) {
    if (!tokenProvider.validToken(createAccessTokenRequest.getRefreshToken())) {
      throw NOT_VALID_REFRESH_TOKEN.newException();
    }
    String nickname = SecurityUtil.getNickname();
    Member member = memberRepository.findByNickname(nickname);

    return tokenProvider.generateToken(member, Duration.ofHours(2));
  }

}