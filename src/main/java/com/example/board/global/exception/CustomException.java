package com.example.board.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final int status;
    private final String code;
    private final String message;

    public CustomException(int status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public CustomException(ExceptionCode code) {
        super(code.getMessage());
        this.status = code.getStatus();
        this.code = code.getCode();
        this.message = code.getMessage();
    }

}