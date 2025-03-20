package com.example.board.board.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record BoardCreateRequest(
        @NotEmpty(message = "제목을 입력하세요.")
        String title,
        @NotEmpty(message = "본문을 입력하세요.")
        String content
) {

}