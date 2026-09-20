package com.sungjujjang.shortgong.domain.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "refresh", timeToLive = 1382400000)
public class RefreshToken {
    @Id
    private String token;

    @Indexed
    private Member member;
}
