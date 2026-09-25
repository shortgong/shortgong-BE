package com.sungjujjang.shortgong.domain.video.entity;

import com.sungjujjang.shortgong.domain.auth.entity.Member;
import com.sungjujjang.shortgong.domain.video.enums.VideoStatus;
import com.sungjujjang.shortgong.global.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "video")
@DynamicInsert
public class Video extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "author_id", nullable = false)
    private Member member;

    @Column(nullable = true)
    @Size(max = 200)
    private String title;

    @Column(nullable = true)
    @Size(max = 200000)
    private String content;

    @Column(nullable = false)
    @Size(max = 20000)
    private String draft;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VideoStatus status;

    @Column(nullable = false)
    @ColumnDefault("0")
    private long viewCount;

    @Column(nullable = false)
    @ColumnDefault("0")
    private long likeCount;
}
