package com.sungjujjang.shortgong.domain.auth.service;

import com.sungjujjang.shortgong.domain.auth.dto.response.TokenResponse;
import com.sungjujjang.shortgong.domain.auth.entity.Member;
import com.sungjujjang.shortgong.domain.auth.entity.RefreshToken;
import com.sungjujjang.shortgong.domain.auth.repository.MemberRepository;
import com.sungjujjang.shortgong.domain.auth.repository.RefreshTokenRepository;
import com.sungjujjang.shortgong.global.exception.exceptions.ForbiddenRefreshTokenException;
import com.sungjujjang.shortgong.global.exception.exceptions.NotFoundExchangeTokenException;
import com.sungjujjang.shortgong.global.exception.exceptions.NotFoundUserException;
import com.sungjujjang.shortgong.global.redis.RedisUtils;
import com.sungjujjang.shortgong.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sungjujjang.shortgong.domain.auth.Auth.EXCHANGETOKEN_AGE;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenService {

    private final RedisUtils redisUtils;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    public RefreshToken createRefreshToken(Member member) {
        String token = RandomStringUtils.randomAlphabetic(20);
        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .member(member)
                .build();
        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public String createExchangeToken(String userId) {
        String token = RandomStringUtils.randomAlphabetic(20);
        redisUtils.setData(token, userId, EXCHANGETOKEN_AGE);
        return token;
    }

    public TokenResponse exchangeToken(String token) {
        String data = redisUtils.getAndDeleteData(token);
        if (data == null) {
            throw NotFoundExchangeTokenException.EXCEPTION;
        }
        Long userId = Long.valueOf(data);
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> NotFoundUserException.EXCEPTION);
        RefreshToken refreshToken = createRefreshToken(member);
        String accessToken = jwtProvider.createAccessToken(member.getId(), String.valueOf(member.getRole()));
        return new TokenResponse(accessToken, refreshToken.getToken());
    }

    public TokenResponse refreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findById(token)
                .orElseThrow(() -> ForbiddenRefreshTokenException.EXCEPTION);

        Member member = refreshToken.getMember();
        refreshTokenRepository.delete(refreshToken);
        RefreshToken newRefreshToken = createRefreshToken(refreshToken.getMember());
        refreshTokenRepository.save(newRefreshToken);


        String accessToken = jwtProvider.createAccessToken(member.getId(), String.valueOf(member.getRole()));
        return new TokenResponse(accessToken, newRefreshToken.getToken());
    }
}
