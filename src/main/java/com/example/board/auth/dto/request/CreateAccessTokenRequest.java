package com.example.board.auth.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class CreateAccessTokenRequest {

  private String refreshToken;

}