package com.sungjujjang.shortgong.domain.auth.controller;

import com.sungjujjang.shortgong.domain.auth.dto.response.AccessTokenResponse;
import com.sungjujjang.shortgong.domain.auth.dto.response.TokenResponse;
import com.sungjujjang.shortgong.domain.auth.service.TokenService;
import com.sungjujjang.shortgong.global.exception.exceptions.ForbiddenRefreshTokenException;
import com.sungjujjang.shortgong.global.response.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

import static com.sungjujjang.shortgong.domain.auth.Auth.REFRESHTOKEN_AGE;

@RestController
@RequestMapping("/api/auth/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @GetMapping("/exchange")
    public ApiResponse<AccessTokenResponse> exchangeToken(
        @RequestParam("token") String token,
        HttpServletResponse httpServletResponse
    ) {
        TokenResponse tokenResponse = tokenService.exchangeToken(token);
        AccessTokenResponse accessTokenResponse = new AccessTokenResponse(tokenResponse.accessToken());

        String sameSiteHeader = setRefreshTokenCookie(tokenResponse.refreshToken(), REFRESHTOKEN_AGE, true);
        httpServletResponse.setHeader("Set-Cookie", sameSiteHeader);

        return ApiResponse.ok(accessTokenResponse);
    }

    @PostMapping("/refresh")
    public ApiResponse<AccessTokenResponse> refreshToken(
            @CookieValue(name = "refreshToken", required = false) String refreshToken,
            HttpServletResponse httpServletResponse
    ) {
        if (refreshToken == null) {
            throw ForbiddenRefreshTokenException.EXCEPTION;
        }
        TokenResponse tokenResponse = tokenService.refreshToken(refreshToken);
        AccessTokenResponse accessTokenResponse = new AccessTokenResponse(tokenResponse.accessToken());

        String sameSiteHeader = setRefreshTokenCookie(tokenResponse.refreshToken(), REFRESHTOKEN_AGE, true);
        httpServletResponse.setHeader("Set-Cookie", sameSiteHeader);

        return ApiResponse.ok(accessTokenResponse);
    }

    private static String setRefreshTokenCookie(String refreshToken, int age, boolean secure) {
        ResponseCookie cookie  = ResponseCookie
                .from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(secure)
                .path("/")
                .maxAge(age)
                .sameSite("Lax") // SameSite=Lax
                .build();

        return cookie.toString();
    }

}
