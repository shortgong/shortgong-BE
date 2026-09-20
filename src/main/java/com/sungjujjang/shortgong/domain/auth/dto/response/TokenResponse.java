package com.sungjujjang.shortgong.domain.auth.dto.response;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
