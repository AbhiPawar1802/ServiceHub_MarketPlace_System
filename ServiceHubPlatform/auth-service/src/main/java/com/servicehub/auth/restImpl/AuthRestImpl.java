package com.servicehub.auth.restImpl;

import com.servicehub.auth.dto.*;
import com.servicehub.auth.rest.AuthRest;
import com.servicehub.auth.service.AuthService;
import com.servicehub.auth.wrapper.AuthRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthRestImpl implements AuthRest {

    @Autowired
    private AuthService authService;

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        return authService.register(request);
    }

    @Override
    public ResponseEntity<?> login(LoginRequest request, HttpServletRequest httpServletRequest) {
        return authService.login(request, httpServletRequest);
    }

    @Override
    public ResponseEntity<?> forgotPassword(ForgetPasswordRequest request) {
        return authService.forgetPassword(request.getEmail());
    }

    @Override
    public ResponseEntity<?> verifyOtp(VerifyOtpRequest request) {
        return authService.verifyOtp(request.getEmail(), request.getOtp());
    }

    @Override
    public ResponseEntity<?> resetPassword(ResetPasswordRequest request) {
        return authService.resetPassword(request.getEmail(), request.getNewPassword());
    }

    @Override
    public ResponseEntity<?> refreshToken(Map<String, String> request) {
        return authService.refreshToken(request.get("refreshToken"));
    }

    @Override
    public ResponseEntity<?> logout(Map<String, String> request) {
        return authService.logout(request.get("refreshToken"));
    }

    @Override
    public ResponseEntity<?> verifyEmail(VerifyEmailRequest request) {
        return authService.verifyEmail(request.getEmail(), request.getOtp());
    }

    @Override
    public ResponseEntity<?> getActiveSessions(String token) {
        return authService.getActiveSessions(token);
    }

    @Override
    public ResponseEntity<?> logoutAllDevices(String authorizationHeader) {
        return authService.logoutAllDevices(authorizationHeader);
    }
}
