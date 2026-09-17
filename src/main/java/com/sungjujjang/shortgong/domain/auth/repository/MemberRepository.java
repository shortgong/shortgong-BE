package com.sungjujjang.shortgong.domain.auth.repository;

import com.sungjujjang.shortgong.domain.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
