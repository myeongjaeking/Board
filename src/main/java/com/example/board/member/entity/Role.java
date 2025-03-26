package com.example.board.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

  MEMBER("ROLE_MEMBER");

  private final String value;

}