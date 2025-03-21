package com.example.board.member.controller;

import com.example.board.global.config.jwt.SecurityUtil;
import com.example.board.member.dto.request.MemberUpdateNicknameRequest;
import com.example.board.member.dto.response.MemberGetResponse;
import com.example.board.member.entity.Member;
import com.example.board.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/get")
  ResponseEntity<Member> getMember() {
    Member member = memberService.getMember();

    return ResponseEntity.status(HttpStatus.CREATED).body(member);
  }

  @PatchMapping("/update")
  ResponseEntity<MemberGetResponse> update(
      @RequestBody MemberUpdateNicknameRequest nicknameRequest) {
    MemberGetResponse memberGetResponse = memberService.update(nicknameRequest);

    return ResponseEntity.status(HttpStatus.OK).body(memberGetResponse);
  }

}