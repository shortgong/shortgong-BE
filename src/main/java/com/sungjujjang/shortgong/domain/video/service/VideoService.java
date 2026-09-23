package com.sungjujjang.shortgong.domain.video.service;

import com.sungjujjang.shortgong.domain.auth.entity.Member;
import com.sungjujjang.shortgong.domain.auth.repository.MemberRepository;
import com.sungjujjang.shortgong.domain.video.dto.request.VideoCreateRequest;
import com.sungjujjang.shortgong.domain.video.dto.response.VideoCreateResponse;
import com.sungjujjang.shortgong.domain.video.entity.Video;
import com.sungjujjang.shortgong.domain.video.enums.VideoStatus;
import com.sungjujjang.shortgong.domain.video.repository.VideoRepository;
import com.sungjujjang.shortgong.global.exception.exceptions.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class VideoService {

    private final VideoRepository videoRepository;
    private final MemberRepository memberRepository;

    public VideoCreateResponse createVideo(Long userId, VideoCreateRequest request) {
        // TODO: message queue 구현 후 push 구현

        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> NotFoundUserException.EXCEPTION);

        Video video = Video.builder()
                .member(member)
                .draft(request.content())
                .status(VideoStatus.PROCESSING)
                .build();

        videoRepository.save(video);

        return new VideoCreateResponse(video.getStatus(), video.getId());
    }
}
