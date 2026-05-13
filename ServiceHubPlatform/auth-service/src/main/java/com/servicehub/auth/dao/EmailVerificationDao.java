package com.servicehub.auth.dao;

import com.servicehub.auth.entity.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationDao extends JpaRepository<EmailVerificationToken, Long> {

    Optional<EmailVerificationToken> findByEmailAndOtpAndUsedFalse(String email, String otp);

}
