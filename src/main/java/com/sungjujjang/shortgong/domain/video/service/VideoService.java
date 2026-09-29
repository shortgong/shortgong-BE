package com.sungjujjang.shortgong.domain.video.service;

import com.sungjujjang.shortgong.domain.auth.entity.Member;
import com.sungjujjang.shortgong.domain.auth.repository.MemberRepository;
import com.sungjujjang.shortgong.domain.video.dto.queue.VideoCreateQueue;
import com.sungjujjang.shortgong.domain.video.dto.request.VideoCreateRequest;
import com.sungjujjang.shortgong.domain.video.dto.response.VideoCreateResponse;
import com.sungjujjang.shortgong.domain.video.dto.response.VideoResponse;
import com.sungjujjang.shortgong.domain.video.entity.Video;
import com.sungjujjang.shortgong.domain.video.enums.VideoStatus;
import com.sungjujjang.shortgong.domain.video.repository.VideoRepository;
import com.sungjujjang.shortgong.global.exception.exceptions.NotFoundUserException;
import com.sungjujjang.shortgong.global.exception.exceptions.VideoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class VideoService {

    private final VideoRepository videoRepository;
    private final MemberRepository memberRepository;
    private final RabbitTemplate rabbitTemplate;

    private @Value("${spring.rabbitmq.create_video.queue}") String routingKey;
//    private @Value("${spring.rabbitmq.create_video.queue}") String queue;

    public VideoCreateResponse createVideo(Long userId, VideoCreateRequest request) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> NotFoundUserException.EXCEPTION);

        Video video = Video.builder()
                .member(member)
                .draft(request.content())
                .status(VideoStatus.PROCESSING)
                .build();

        videoRepository.save(video);

        VideoCreateQueue videoCreateQueue = new VideoCreateQueue(video.getId());
        rabbitTemplate.convertAndSend(routingKey, videoCreateQueue);

        return new VideoCreateResponse(video.getStatus(), video.getId());
    }

    public VideoResponse getVideo(Long videoId) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> VideoNotFoundException.EXCEPTION);

        return VideoResponse.of(video);
    }
}
