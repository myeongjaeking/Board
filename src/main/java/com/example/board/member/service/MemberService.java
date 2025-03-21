package com.example.board.member.service;

import com.example.board.global.config.jwt.SecurityUtil;
import com.example.board.member.dto.request.MemberCreateRequest;
import com.example.board.member.entity.Member;
import com.example.board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

  private final MemberRepository memberRepository;

  public Member save(MemberCreateRequest memberCreateRequest) {
    Member member = Member.create()
        .email(memberCreateRequest.email())
        .nickname(memberCreateRequest.nickname())
        .password(memberCreateRequest.password())
        .build();

    memberRepository.save(member);

    return member;
  }

  public void saveRefreshToken(Long memberId, String refreshToken) {
    Member member = findById(memberId);
    member.updateRefreshToken(refreshToken);

    memberRepository.save(member);
  }

  public Member findByRefreshToken(String refreshToken) {
    return memberRepository.findByRefreshToken(refreshToken);
  }

  public Member findByEmail(String email) {
    return memberRepository.findByEmail(email);
  }

  public Member findById(Long memberId) {
    return memberRepository.findById(memberId);
  }

  public Member getMember() {
    String email = SecurityUtil.getUserEmail();

    return memberRepository.findByEmail(email);
  }

  public Long getMemberId() {
    Member member = getMember();

    return member.getId();
  }

}