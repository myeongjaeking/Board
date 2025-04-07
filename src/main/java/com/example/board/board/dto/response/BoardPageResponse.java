package com.example.board.board.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record BoardPageResponse(
    String title,
    Long id,
    LocalDateTime createTime,
    int viewCount
) {

}