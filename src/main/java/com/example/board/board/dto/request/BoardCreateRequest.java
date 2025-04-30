package com.example.board.board.dto.request;

import jakarta.validation.constraints.NotBlank;

public record BoardCreateRequest(
    @NotBlank(message = "제목을 입력하세요.")
    String title,
    @NotBlank(message = "본문을 입력하세요.")
    String content
) {

}