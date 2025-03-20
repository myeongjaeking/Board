package com.example.board.global.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalExceptionCode implements ExceptionCode {

    INVALID_INPUT(BAD_REQUEST, "유효한 입력 형식이 아닙니다."),
    NOT_FOUND_BOARD(BAD_REQUEST, "존재하지 않는 글입니다."),
    SERVER_ERROR(INTERNAL_SERVER_ERROR, "예상치 못한 문제가 발생했습니다.");;

    private final HttpStatus status;
    private final String message;

    @Override
    public String getCode() {
        return this.name();
    }

}