package com.example.board.member.controller;

import com.example.board.member.dto.request.MemberUpdateNicknameRequest;
import com.example.board.member.dto.response.MemberUpdateResponse;
import com.example.board.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @PatchMapping("/update")
  public ResponseEntity<MemberUpdateResponse> update(
      @Valid @RequestBody MemberUpdateNicknameRequest request) {
    MemberUpdateResponse memberUpdateResponse = memberService.update(request);

    return ResponseEntity.status(HttpStatus.OK).body(memberUpdateResponse);
  }

}