package com.sungjujjang.shortgong.domain.video.repository;

import com.sungjujjang.shortgong.domain.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
