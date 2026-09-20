package com.sungjujjang.shortgong.domain.auth.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Providers {
    GOOGLE("GOOGLE");

    private final String key;
}
