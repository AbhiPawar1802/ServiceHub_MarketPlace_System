package com.servicehub.auth.dao;

import com.servicehub.auth.POJO.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenDao extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByEmailAndOtpAndUsedFalse(String email, String otp);

    Optional<PasswordResetToken> findTopByEmailOrderByExpiryTimeDesc(String email);

}
