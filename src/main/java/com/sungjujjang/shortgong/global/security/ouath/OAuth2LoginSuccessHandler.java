package com.sungjujjang.shortgong.global.security.ouath;

import com.sungjujjang.shortgong.domain.auth.entity.Member;
import com.sungjujjang.shortgong.domain.auth.entity.Provider;
import com.sungjujjang.shortgong.domain.auth.enums.Providers;
import com.sungjujjang.shortgong.domain.auth.enums.Role;
import com.sungjujjang.shortgong.domain.auth.repository.MemberRepository;
import com.sungjujjang.shortgong.domain.auth.repository.ProviderRepository;
import com.sungjujjang.shortgong.domain.auth.service.TokenService;
import com.sungjujjang.shortgong.global.exception.exceptions.NotFoundUserException;
import com.sungjujjang.shortgong.global.security.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler
        implements AuthenticationSuccessHandler {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final TokenService tokenService;
    private final ProviderRepository providerRepository;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException {

        OAuth2User oauth2User =
                (OAuth2User) authentication.getPrincipal();

        if (oauth2User == null) {
            throw NotFoundUserException.EXCEPTION;
        }

        String email = oauth2User.getAttribute("email");
        String userName = oauth2User.getAttribute("name");
        String providerId = oauth2User.getAttribute("sub");
        String profileImg = oauth2User.getAttribute("picture");

        Long userId;
        Optional<Provider> provider = providerRepository.findByEmail(email);
        if (provider.isEmpty()) {
            Member member = Member.builder()
                    .username(userName)
                    .role(Role.USER)
                    .profileImageUrl(profileImg)
                    .build();
            memberRepository.save(member);
            Provider newProvider = Provider.builder()
                    .member(member)
                    .provider(Providers.GOOGLE)
                    .providerId(providerId)
                    .email(email)
                    .build();
            providerRepository.save(newProvider);
            userId = member.getId();
        } else {
            userId = provider.get().getMember().getId();
        }

        String key = tokenService.createExchangeToken(String.valueOf(userId));
        response.sendRedirect(
    "http://localhost:8080/oauth/callback"
            + "?key=" + key
        );
    }
}