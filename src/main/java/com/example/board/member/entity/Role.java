package com.example.board.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum Role {

  MEMBER("ROLE_MEMBER");

  private final String value;

}