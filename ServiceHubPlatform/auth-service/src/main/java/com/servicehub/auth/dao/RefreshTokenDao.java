package com.servicehub.auth.dao;

import com.servicehub.auth.POJO.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenDao extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByTokenAndRevokedFalse(String token);
    List<RefreshToken> findByEmailAndRevokedFalse(String email);
}
