package com.example.board.member.dto.request;

public record MemberCreateRequest(
    String nickname,
    String email,
    String password
) {

}
