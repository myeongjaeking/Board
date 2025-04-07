package com.example.board.board.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record BoardGetResponse(
    String title,
    String content,
    LocalDateTime createTime,
    String nickname,
    int viewCount,
    int likeCount
) {

}