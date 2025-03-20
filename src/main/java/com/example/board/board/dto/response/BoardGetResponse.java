package com.example.board.board.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BoardGetResponse(
        String title,
        String content,
        LocalDateTime createTime,
        int viewCount,
        int likeCount
) {

}