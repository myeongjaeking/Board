package com.example.board.global.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;

public record ExceptionResponse(int status, String code, String message) {

    @Builder
    public ExceptionResponse {
    }

    public static ExceptionResponse from(CustomException exception) {
        return ExceptionResponse.builder()
            .status(exception.getStatus())
            .code(exception.getCode())
            .message(exception.getMessage())
            .build();
    }

    public static ExceptionResponse of(int status, String code, String message) {
        return ExceptionResponse.builder()
            .status(status)
            .code(code)
            .message(message)
            .build();
    }
}