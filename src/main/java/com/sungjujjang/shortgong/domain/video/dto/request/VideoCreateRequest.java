package com.sungjujjang.shortgong.domain.video.dto.request;

import jakarta.validation.constraints.Size;

public record VideoCreateRequest(
    @Size(max = 10000)
    String content
) {
}
