package com.sungjujjang.shortgong.domain.video.controller;

import com.sungjujjang.shortgong.domain.video.dto.request.VideoCreateRequest;
import com.sungjujjang.shortgong.domain.video.dto.response.VideoCreateResponse;
import com.sungjujjang.shortgong.domain.video.dto.response.VideoResponse;
import com.sungjujjang.shortgong.domain.video.service.VideoService;
import com.sungjujjang.shortgong.global.anotation.currentUserId.CurrentUserId;
import com.sungjujjang.shortgong.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/video")
public class VideoController {

    private final VideoService videoService;

    @PostMapping
    public ApiResponse<VideoCreateResponse> createVideo(
            @Valid @RequestBody VideoCreateRequest videoCreateRequest,
            @Parameter(hidden = true) @CurrentUserId Long userId
    ) {
        VideoCreateResponse videoCreateResponse = videoService.createVideo(userId, videoCreateRequest);
        return ApiResponse.ok(videoCreateResponse);
    }

    @GetMapping("/{videoId}")
    public ApiResponse<VideoResponse> getVideo(
            @PathVariable @RequestParam(required = true) long videoId
    ) {
        VideoResponse videoResponse = videoService.getVideo(videoId);
        return ApiResponse.ok(videoResponse);
    }
}
