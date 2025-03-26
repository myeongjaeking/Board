package com.example.board.member.service;

import com.example.board.global.common.SecurityUtil;
import com.example.board.member.dto.request.MemberCreateRequest;
import com.example.board.member.dto.request.MemberUpdateNicknameRequest;
import com.example.board.member.dto.response.MemberCreateResponse;
import com.example.board.member.dto.response.MemberUpdateResponse;
import com.example.board.member.entity.Member;
import com.example.board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

  private final MemberRepository memberRepository;

  public MemberCreateResponse save(MemberCreateRequest memberCreateRequest) {
    Member member = Member.create()
        .email(memberCreateRequest.email())
        .nickname(memberCreateRequest.nickname())
        .password(memberCreateRequest.password())
        .build();

    Long memberId = memberRepository.save(member);

    return MemberCreateResponse.builder()
        .memberId(memberId)
        .build();
  }

  @Transactional
  public MemberUpdateResponse update(MemberUpdateNicknameRequest request) {
    Member member = SecurityUtil.getMember();

    member.updateNickname(request.nickname());

    return MemberUpdateResponse.builder()
        .memberId(member.getId())
        .build();
  }

}