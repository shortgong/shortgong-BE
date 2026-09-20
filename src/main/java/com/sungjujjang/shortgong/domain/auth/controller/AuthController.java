package com.sungjujjang.shortgong.domain.auth.controller;

import com.sungjujjang.shortgong.domain.auth.dto.response.AccessTokenResponse;
import com.sungjujjang.shortgong.domain.auth.dto.response.MemberResponse;
import com.sungjujjang.shortgong.domain.auth.service.AuthService;
import com.sungjujjang.shortgong.domain.auth.service.TokenService;
import com.sungjujjang.shortgong.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @DeleteMapping("/logout")
    public ApiResponse<Void> logout(
            @CookieValue(name = "refreshToken", required = false) String refreshToken
    ) {
        authService.logout(refreshToken);
        return ApiResponse.ok();
    }

    @GetMapping("/me")
    public ApiResponse<MemberResponse> me() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        MemberResponse memberResponse = authService.me(Long.valueOf(userId));
        return ApiResponse.ok(memberResponse);
    }
}
