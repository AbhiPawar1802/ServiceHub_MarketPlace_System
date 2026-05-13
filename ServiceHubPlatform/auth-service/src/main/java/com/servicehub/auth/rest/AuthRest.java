package com.servicehub.auth.rest;

import com.servicehub.auth.dto.*;
import com.servicehub.auth.wrapper.AuthRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/auth")
public interface AuthRest {

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody RegisterRequest request);

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest);

   @PostMapping("/forgot-password")
    ResponseEntity<?> forgotPassword(@RequestBody ForgetPasswordRequest request);

   @PostMapping("/verify-otp")
    ResponseEntity<?> verifyOtp(@RequestBody VerifyOtpRequest request);

   @PostMapping("/reset-password")
    ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request);

   @PostMapping("/refresh-token")
    ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request);

   @PostMapping("/logout")
    ResponseEntity<?> logout(@RequestBody Map<String, String> request);

   @PostMapping("/verify-email")
    ResponseEntity<?> verifyEmail(@RequestBody VerifyEmailRequest request);

   @GetMapping("/sessions")
    ResponseEntity<?> getActiveSessions(@RequestHeader("Authorization") String token);

   @PostMapping("/logout-all")
    ResponseEntity<?> logoutAllDevices(@RequestHeader("Authorization") String authorizationHeader);


}
