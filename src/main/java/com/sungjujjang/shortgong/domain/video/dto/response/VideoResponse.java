package com.sungjujjang.shortgong.domain.video.dto.response;

import com.sungjujjang.shortgong.domain.auth.dto.response.MemberResponse;
import com.sungjujjang.shortgong.domain.auth.entity.Member;
import com.sungjujjang.shortgong.domain.video.entity.Video;
import com.sungjujjang.shortgong.domain.video.enums.VideoStatus;

public record VideoResponse(
        long id,
        String title,
        String content,
        VideoStatus status,
        String draft,
        long likeCount,
        long viewCount,
        MemberResponse author
) {
    public static VideoResponse of(Video video) {
        return new VideoResponse(
                video.getId(),
                video.getTitle(),
                video.getContent(),
                video.getStatus(),
                video.getDraft(),
                video.getLikeCount(),
                video.getViewCount(),
                MemberResponse.of(video.getMember())
        );
    }

}
