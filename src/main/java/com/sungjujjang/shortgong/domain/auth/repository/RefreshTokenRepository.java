package com.sungjujjang.shortgong.domain.auth.repository;

import com.sungjujjang.shortgong.domain.auth.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
