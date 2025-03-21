package com.example.board.global.config.jwt.service;

import static com.example.board.global.exception.GlobalExceptionCode.NOT_MATCH_PASSWORD;

import com.example.board.global.exception.CustomException;
import com.example.board.member.dto.request.MemberCreateRequest;
import com.example.board.member.dto.request.MemberLoginRequest;
import com.example.board.member.entity.Member;
import com.example.board.member.service.MemberService;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final MemberService memberService;
  private final TokenProvider tokenProvider;

  public String login(MemberLoginRequest memberLoginRequest) {
    Member member = memberService.findByEmail(memberLoginRequest.getEmail());
    validatePassword(member, memberLoginRequest.getPassword());

    String accessToken = tokenProvider.generateToken(member, Duration.ofHours(2));
    String refreshToken = tokenProvider.generateToken(member, Duration.ofDays(14));

    saveRefreshToken(member.getId(), refreshToken);

    return accessToken;
  }

  public Member signup(MemberCreateRequest memberCreateRequest) {
    return memberService.save(memberCreateRequest);
  }

  private void validatePassword(Member member, String password) {
    if (!member.getPassword().equals(password)) {
      throw new CustomException(NOT_MATCH_PASSWORD);
    }
  }

  private void saveRefreshToken(Long userId, String refreshToken) {
    memberService.saveRefreshToken(userId, refreshToken);
  }

}