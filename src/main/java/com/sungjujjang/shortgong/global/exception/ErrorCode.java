package com.sungjujjang.shortgong.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_VALID_DTO_ERR(400, "NOT_VALID_DTO_ERR", "유효하지 않은 요청입니다."),

    UNAUTHORIZED(401, "UNAUTHORIZED", "인증이 필요합니다."),

    FORBIDDEN(403, "FORBIDDEN", "권한이 없습니다."),
    FORBIDDEN_REFRESH_TOKEN(403, "FORBIDDEN_REFRESH_TOKEN", "refresh token이 없습니다"),

    NOT_FOUND(404, "NOT_FOUND", "리소스가 없습니다."),
    NOT_FOUND_EXCHANGE_TOKEN(404, "NOT_FOUND_EXCHANGE_TOKEN", "교환 코드가 존재하지 않습니다."),
    NOT_FOUND_USER(404, "NOT_FOUND_USER", "유저를 찾을 수 없습니다."),

    INTERNAL_SERVER_ERR(500, "INTERNAL_SERVER_ERR", "서버 측 오류가 발생했습니다.");

    private Integer statusCode;
    private String errorCode;
    private String errorMessage;
}
