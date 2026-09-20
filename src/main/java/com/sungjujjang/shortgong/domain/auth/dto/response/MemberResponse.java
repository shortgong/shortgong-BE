package com.sungjujjang.shortgong.domain.auth.dto.response;

import com.sungjujjang.shortgong.domain.auth.entity.Member;
import com.sungjujjang.shortgong.domain.auth.enums.Role;

public record MemberResponse(
        long id,
        String username,
        Role role,
        String profileImageUrl
) {

    public static MemberResponse of(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getUsername(),
                member.getRole(),
                member.getProfileImageUrl()
        );
    }

}
