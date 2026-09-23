package com.sungjujjang.shortgong.domain.video.dto.response;

import com.sungjujjang.shortgong.domain.video.enums.VideoStatus;

public record VideoCreateResponse(
        VideoStatus status,
        Long id
) {
}
