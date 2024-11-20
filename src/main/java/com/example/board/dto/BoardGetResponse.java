package com.example.board.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BoardGetResponse(
        String title,
        String content,
        LocalDate createBoard,
        int viewCount,
        int likeCount
) {

}


