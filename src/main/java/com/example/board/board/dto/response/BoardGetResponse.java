package com.example.board.board.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record BoardGetResponse(
    Long id,
    String title,
    String content,
    LocalDateTime createTime,
    int viewCount
) {
}