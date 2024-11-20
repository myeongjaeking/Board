package com.example.board.dto;


import jakarta.validation.constraints.NotEmpty;

public record BoardCreateRequest(
        @NotEmpty(message = "제목을 입력해주세요")
        String title,
        @NotEmpty(message = "내용을 입력해주세요")
        String content
) {

}