package com.sungjujjang.shortgong.domain.auth.entity;

import com.sungjujjang.shortgong.domain.auth.enums.Providers;
import com.sungjujjang.shortgong.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "provider")
public class Provider extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customer_id", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Providers provider;

    @Column(nullable = false)
    private String providerId;

    @Column(nullable = true)
    private String email;
}
