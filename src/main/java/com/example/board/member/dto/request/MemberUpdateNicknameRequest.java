package com.example.board.member.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MemberUpdateNicknameRequest (
    @NotBlank(message = "닉네임을 입력해주세요.")
    String nickname
){

}