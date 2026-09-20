package com.sungjujjang.shortgong.domain.auth.repository;

import com.sungjujjang.shortgong.domain.auth.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Optional<Provider> findByEmail(String email);
}
