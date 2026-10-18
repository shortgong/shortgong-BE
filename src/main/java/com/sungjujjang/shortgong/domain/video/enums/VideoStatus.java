package com.sungjujjang.shortgong.domain.video.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum VideoStatus {
    PROCESSING("PROCESSING"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED");

    private final String status;
}
