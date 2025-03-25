package com.example.board.member.service;

import com.example.board.global.common.SecurityUtil;
import com.example.board.member.dto.request.MemberCreateRequest;
import com.example.board.member.dto.request.MemberUpdateNicknameRequest;
import com.example.board.member.dto.response.MemberGetResponse;
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

  public MemberGetResponse update(MemberUpdateNicknameRequest nicknameRequest) {
    Member member = SecurityUtil.getMember();

    member.updateNickname(nicknameRequest.nickname());

    memberRepository.save(member);

    return MemberGetResponse.builder()
        .nickname(nicknameRequest.nickname())
        .build();
  }

}