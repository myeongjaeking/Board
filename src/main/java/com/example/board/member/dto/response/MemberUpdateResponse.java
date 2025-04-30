package com.example.board.member.dto.response;

import lombok.Builder;

@Builder
public record MemberUpdateResponse(
    Long memberId
) {
}