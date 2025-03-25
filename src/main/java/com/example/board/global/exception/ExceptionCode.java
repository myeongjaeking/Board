package com.example.board.global.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionCode {

    int getStatus();

    String getCode();

    String getMessage();

}