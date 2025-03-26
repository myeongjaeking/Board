package com.example.board.member.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MemberLoginRequest(
    @NotBlank(message = "이메일을 입력해주세요.")
    String email,
    @NotBlank(message = "패스워드를 입력해주세요.")
    String password
) {

}