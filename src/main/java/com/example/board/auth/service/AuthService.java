package com.example.board.auth.service;

import static com.example.board.global.exception.GlobalExceptionCode.DUPLICATION_NICKNAME;
import static com.example.board.global.exception.GlobalExceptionCode.NOT_MATCH_PASSWORD;

import com.example.board.auth.dto.response.TokenResponse;
import com.example.board.global.common.SecurityUtil;
import com.example.board.global.security.jwt.TokenProvider;
import com.example.board.member.dto.request.MemberCreateRequest;
import com.example.board.member.dto.request.MemberLoginRequest;
import com.example.board.member.entity.Member;
import com.example.board.member.repository.MemberRepository;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

  private final MemberRepository memberRepository;
  private final TokenProvider tokenProvider;

  public TokenResponse login(MemberLoginRequest memberLoginRequest) {
    Member member = memberRepository.findByEmail(memberLoginRequest.email());
    member.validatePassword(memberLoginRequest.password());

    String accessToken = tokenProvider.generateAccessToken(member, Duration.ofHours(2));
    String refreshToken = tokenProvider.generateRefreshToken(member, Duration.ofDays(14));

    saveRefreshToken(member.getId(), refreshToken);

    return TokenResponse.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }

  public Long signup(MemberCreateRequest memberCreateRequest) {
    if (memberRepository.existsByNickname(memberCreateRequest.nickname())) {
      throw DUPLICATION_NICKNAME.newException();
    }

    Member member = Member.create(memberCreateRequest.email(),
        memberCreateRequest.nickname(),
        memberCreateRequest.password());

    memberRepository.save(member);
    return member.getId();
  }

  private void saveRefreshToken(Long memberId, String refreshToken) {
    Member member = memberRepository.findById(memberId);
    member.updateRefreshToken(refreshToken);

    memberRepository.save(member);
  }

  public Long logout() {
    String nickname = SecurityUtil.getNickname();
    Member member = memberRepository.findByNickname(nickname);

    member.updateRefreshToken(null);

    return member.getId();
  }

}