package com.example.board.member.dto.request;

import lombok.Builder;

@Builder
public record MemberUpdateNicknameRequest (
    String nickname
){

}