package com.example.board.member.controller;

import com.example.board.global.config.jwt.SecurityUtil;
import com.example.board.member.entity.Member;
import com.example.board.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/get")
  ResponseEntity<Member> getMember() {
    String email = SecurityUtil.getUserEmail();
    Member member = memberService.findByEmail(email);

    return ResponseEntity.status(HttpStatus.CREATED).body(member);
  }

}