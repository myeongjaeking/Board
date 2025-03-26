package com.example.board.global.exception;

import lombok.Builder;

@Builder
public record ExceptionResponse(int status, String code, String message) {

    public static ExceptionResponse from(CustomException exception) {
        return ExceptionResponse.builder()
            .status(exception.getStatus())
            .code(exception.getCode())
            .message(exception.getMessage())
            .build();
    }

    public static ExceptionResponse of(ExceptionCode exceptionCode, String message) {
        return ExceptionResponse.builder()
            .status(exceptionCode.getStatus())
            .code(exceptionCode.getCode())
            .message(message)
            .build();
    }

}