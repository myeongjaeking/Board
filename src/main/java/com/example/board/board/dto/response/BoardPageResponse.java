package com.example.board.board.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BoardPageResponse(
    String title,
    Long id,
    LocalDateTime createTime,
    int viewCount
) {

}