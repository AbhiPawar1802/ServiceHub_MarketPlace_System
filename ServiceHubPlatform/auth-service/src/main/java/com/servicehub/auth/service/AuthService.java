package com.servicehub.auth.service;

import com.servicehub.auth.dto.LoginRequest;
import com.servicehub.auth.dto.RegisterRequest;
import com.servicehub.auth.wrapper.AuthRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> register(RegisterRequest request);
    ResponseEntity<?> login(LoginRequest request, HttpServletRequest httpServletRequest);
    ResponseEntity<?> forgetPassword(String email);
    ResponseEntity<?> verifyOtp(String email, String otp);
    ResponseEntity<?> resetPassword(String email, String newPassword);
    ResponseEntity<?> refreshToken(String refreshToken);
    ResponseEntity<?> logout(String refreshToken);
    ResponseEntity<?> verifyEmail(String email, String otp);
    ResponseEntity<?> getActiveSessions(String token);
    ResponseEntity<?> logoutAllDevices(String authorizationHeader);
}
