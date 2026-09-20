package com.sungjujjang.shortgong.domain.auth.service;

import com.sungjujjang.shortgong.domain.auth.dto.response.MemberResponse;
import com.sungjujjang.shortgong.domain.auth.entity.Member;
import com.sungjujjang.shortgong.domain.auth.repository.MemberRepository;
import com.sungjujjang.shortgong.domain.auth.repository.RefreshTokenRepository;
import com.sungjujjang.shortgong.global.exception.exceptions.NotFoundUserException;
import com.sungjujjang.shortgong.global.redis.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final MemberRepository memberRepository;
    private final RedisUtils redisUtils;
    private final RefreshTokenRepository refreshTokenRepository;

    public void logout(String refreshToken) {
        refreshTokenRepository.deleteById(refreshToken);
    }

    public MemberResponse me(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> NotFoundUserException.EXCEPTION);

        return MemberResponse.of(member);
    }
}
