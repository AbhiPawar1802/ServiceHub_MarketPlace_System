package com.servicehub.auth.dao;

import com.servicehub.auth.entity.RateLimit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RateLimitDao extends JpaRepository<RateLimit, Long> {
    Optional<RateLimit> findByKey(String key);
}
