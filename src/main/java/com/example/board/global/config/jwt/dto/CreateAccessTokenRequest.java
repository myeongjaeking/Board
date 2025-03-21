package com.example.board.global.config.jwt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateAccessTokenRequest {

  private String refreshToken;

}