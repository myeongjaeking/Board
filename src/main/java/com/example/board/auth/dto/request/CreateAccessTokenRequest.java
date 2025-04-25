package com.example.board.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateAccessTokenRequest(
    @NotBlank(message = "RefreshToken 이 비어있습니다.")
    String refreshToken
) {
}