package com.example.board.global.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalExceptionCode implements ExceptionCode {

    INVALID_INPUT(BAD_REQUEST.value(), "유효한 입력 형식이 아닙니다."),

    NOT_FOUND_BOARD(NOT_FOUND.value(), "존재하지 않는 글입니다."),
    FORBIDDEN_ACCESS_BOARD(FORBIDDEN.value(), "게시글 접근 권한이 없습니다."),

    NOT_FOUND_COMMENT(NOT_FOUND.value(), "존재하지 않는 댓글입니다."),
    FORBIDDEN_ACCESS_COMMENT(FORBIDDEN.value(), "댓글 접근 권한이 없습니다."),

    NOT_FOUND_BOOKMARK(NOT_FOUND.value(), "존재하지 않는 즐겨찾기입니다."),
    FORBIDDEN_ACCESS_BOOKMARK(FORBIDDEN.value(), "즐겨찾기 접근 권한이 없습니다."),

    NOT_FOUND_LIKE(NOT_FOUND.value(),"좋아요 삭제 실패했습니다."),
    FORBIDDEN_ACCESS_LIKE(FORBIDDEN.value(), "좋아요 접근 권한이 없습니다."),

    SERVER_ERROR(INTERNAL_SERVER_ERROR.value(), "예상치 못한 문제가 발생했습니다."),

    NOT_MATCH_PASSWORD(UNAUTHORIZED.value(), "패스워드가 일치하지 않습니다."),
    NOT_VALID_REFRESH_TOKEN(NOT_FOUND.value(), "리프레시 토큰을 찾을 수 없습니다."),
    NO_AUTHENTICATED(UNAUTHORIZED.value(), "사용자가 인증되지 않았습니다.");

    private final int status;
    private final String message;

    @Override
    public String getCode() {
        return this.name();
    }

    public CustomException newException() {
        return new CustomException(this);
    }

}