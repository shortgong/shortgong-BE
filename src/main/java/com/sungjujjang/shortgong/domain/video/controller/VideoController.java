package com.sungjujjang.shortgong.domain.video.controller;

import com.sungjujjang.shortgong.domain.video.dto.response.VideoCreateResponse;
import com.sungjujjang.shortgong.domain.video.service.VideoService;
import com.sungjujjang.shortgong.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/video")
public class VideoController {

    private final VideoService videoService;

    @PostMapping
    public ApiResponse<VideoCreateResponse> createVideo(

    ) {

    }

}
