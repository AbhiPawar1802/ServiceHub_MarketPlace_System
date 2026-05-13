package com.servicehub.auth.utils;

import com.servicehub.auth.dao.RateLimitDao;
import com.servicehub.auth.entity.RateLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RateLimitUtil {

    private static final int MAX_ATTEMPTS = 5;
    private static final int BLOCK_MINUTES = 15;

    @Autowired
    private RateLimitDao rateLimitDao;

    public void validate(String key) {
        RateLimit limit = rateLimitDao
                .findByKey(key)
                .orElse(RateLimit.builder()
                        .key(key)
                        .attempts(0)
                        .lastAttempt(LocalDateTime.now())
                        .build());

        if (limit.getAttempts() >= MAX_ATTEMPTS &&
                limit.getLastAttempt()
                        .isAfter(LocalDateTime.now().minusMinutes(BLOCK_MINUTES))) {
            throw new RuntimeException("Too many attempts. try later.");
        }
        limit.setAttempts(limit.getAttempts() + 1);
        limit.setLastAttempt(LocalDateTime.now());
        rateLimitDao.save(limit);
    }

    public void reset(String key) {
        rateLimitDao.findByKey(key).ifPresent(rateLimitDao::delete);
    }
}
