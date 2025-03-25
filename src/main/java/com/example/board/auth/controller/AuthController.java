package com.example.board.auth.controller;

import com.example.board.auth.dto.CreateAccessTokenRequest;
import com.example.board.auth.service.AuthService;
import com.example.board.auth.service.TokenService;
import com.example.board.member.dto.request.MemberCreateRequest;
import com.example.board.member.dto.request.MemberLoginRequest;
import com.example.board.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {


  private final AuthService authService;
  private final TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody MemberLoginRequest loginRequest) {
    String accessToken = authService.login(loginRequest);

    return ResponseEntity.status(HttpStatus.OK).body(accessToken);
  }

  @PostMapping("/signup")
  ResponseEntity<Long> signup(@RequestBody MemberCreateRequest memberCreateRequest) {
    Long memberId = authService.signup(memberCreateRequest);

    return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
  }

  @PostMapping("/refresh")
  public ResponseEntity<String> createNewAccessToken(
      @RequestBody CreateAccessTokenRequest request) {
    String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(newAccessToken);
  }

  @DeleteMapping("/logout")
  public ResponseEntity<Long> logout() {
    Long memberId = authService.logout();

    return ResponseEntity.status(HttpStatus.OK).body(memberId);
  }

}