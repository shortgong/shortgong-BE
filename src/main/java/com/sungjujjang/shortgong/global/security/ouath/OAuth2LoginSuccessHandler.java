package com.sungjujjang.shortgong.global.security.ouath;

import com.sungjujjang.shortgong.domain.auth.entity.Member;
import com.sungjujjang.shortgong.domain.auth.repository.MemberRepository;
import com.sungjujjang.shortgong.global.security.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler
        implements AuthenticationSuccessHandler {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {

        OAuth2User oauth2User =
                (OAuth2User) authentication.getPrincipal();

        String email =
                oauth2User.getAttribute("email");

//        Member member = memberRepository
//                .findByEmail(email)
//                .orElseThrow();

        String accessToken = email;

        String refreshToken = "test2134";

        response.sendRedirect(
                "http://localhost:8080/oauth/callback"
                        + "?accessToken=" + accessToken
                        + "&refreshToken=" + refreshToken
        );
    }
}