package com.servicehub.auth.serviceImpl;

import com.servicehub.auth.JWT.JwtUtil;
import com.servicehub.auth.POJO.AuthUser;
import com.servicehub.auth.POJO.PasswordResetToken;
import com.servicehub.auth.POJO.RefreshToken;
import com.servicehub.auth.constents.AuthConstents;
import com.servicehub.auth.dao.AuthUserDao;
import com.servicehub.auth.dao.EmailVerificationDao;
import com.servicehub.auth.dao.PasswordResetTokenDao;
import com.servicehub.auth.dao.RefreshTokenDao;
import com.servicehub.auth.dto.AuthResponse;
import com.servicehub.auth.dto.LoginRequest;
import com.servicehub.auth.dto.RegisterRequest;
import com.servicehub.auth.entity.EmailVerificationToken;
import com.servicehub.auth.service.AuthService;
import com.servicehub.auth.utils.EmailUtils;
import com.servicehub.auth.utils.RateLimitUtil;
import com.servicehub.auth.wrapper.AuthRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthUserDao authUserDao;

    @Autowired
    private RefreshTokenDao refreshTokenDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetTokenDao tokenDao;

    @Autowired
    private EmailVerificationDao emailVerificationDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmailUtils emailUtils;

    @Autowired
    private RateLimitUtil rateLimitUtil;

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        try {
            if (authUserDao.findByEmail(request.getEmail()).isPresent()) {
                return new ResponseEntity<>(
                        AuthConstents.EMAIL_ALREADY_EXISTS,
                        HttpStatus.BAD_REQUEST
                );
            }

            AuthUser user = new AuthUser();
            user.setName(request.getName());
            user.setPhone(request.getPhone());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(request.getRole().toUpperCase());
            user.setStatus("ACTIVE");
            user.setEmailVerified(false);

            authUserDao.save(user);

            String otp = String.valueOf(100000 + new Random().nextInt(900000));
            emailVerificationDao.save(
                    EmailVerificationToken.builder()
                            .email(user.getEmail())
                            .otp(otp)
                            .used(false)
                            .expiryTime(LocalDateTime.now().plusMinutes(10))
                            .build()
            );

            emailUtils.sendOtpEmail(user.getEmail(), otp);

            return new ResponseEntity<>(
                    AuthConstents.USER_REGISTERED, HttpStatus.CREATED
            );
        } catch (Exception e) {
            log.error("Error in register", e);
        }
        return new ResponseEntity<>(
                AuthConstents.SOMETHING_WENT_WRONG,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @Override
    public ResponseEntity<?> login(LoginRequest request, HttpServletRequest httpServletRequest) {
        try {
            rateLimitUtil.validate(request.getEmail());

            Optional<AuthUser> userOpt = authUserDao.findByEmail(request.getEmail());
            if (userOpt.isEmpty()) {
                return new ResponseEntity<>(
                        AuthConstents.INVALID_CREDENTIALS,
                        HttpStatus.UNAUTHORIZED
                );
            }

            AuthUser user = userOpt.get();

            if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
                return new ResponseEntity<>(
                        AuthConstents.USER_DISABLED,
                        HttpStatus.UNAUTHORIZED
                );
            }

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return new ResponseEntity<>(
                        AuthConstents.INVALID_CREDENTIALS,
                        HttpStatus.UNAUTHORIZED
                );
            }

            if(!user.isEmailVerified()){
                return new ResponseEntity<>(
                        "Please verify your email first",
                        HttpStatus.UNAUTHORIZED
                );
            }

            String accessToken = jwtUtil.generateAccessToken(
                    user.getId(),
                    user.getEmail(),
                    user.getRole());
            String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());
            refreshTokenDao.save(
                    RefreshToken.builder()
                            .email(user.getEmail())
                            .token(refreshToken)
                            .revoked(false)
                            .expiryTime(LocalDateTime.now().plusDays(7))
                            .deviceId(request.getDeviceId())
                            .deviceType(request.getDeviceType())
                            .userAgent(httpServletRequest.getHeader("User-Agent"))
                            .ipAddress(httpServletRequest.getRemoteAddr())
                            .createdAt(LocalDateTime.now())
                            .build()
            );

            return ResponseEntity.ok(
                    new AuthResponse(
                            accessToken,
                            refreshToken,
                            user.getRole()
                    )
            );

        } catch (Exception e) {
            log.error("Error in login", e);
        }
        return new ResponseEntity<>(
                AuthConstents.SOMETHING_WENT_WRONG,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @Override
    public ResponseEntity<?> forgetPassword(String email) {
        rateLimitUtil.validate(email);
        Optional<AuthUser> userOpt = authUserDao.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Email not registered");
        }
        String otp = String.valueOf(100000 + new Random().nextInt(900000));


        PasswordResetToken token = PasswordResetToken.builder()
                .email(email)
                .otp(otp)
                .used(false)
                .expiryTime(LocalDateTime.now().plusMinutes(5))
                .build();
        tokenDao.save(token);
        emailUtils.sendOtpEmail(email, otp);

        return ResponseEntity.ok("OTP sent to email.");
    }

    @Override
    public ResponseEntity<String> verifyOtp(String email, String otp) {
        rateLimitUtil.validate(email);
        Optional<PasswordResetToken> tokenOpt = tokenDao.findByEmailAndOtpAndUsedFalse(email, otp);

        if (tokenOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }

        if (tokenOpt.get().getExpiryTime().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("OTP expired");
        }

        rateLimitUtil.reset(email);
        return ResponseEntity.ok("OTP verified");
    }

    @Override
    public ResponseEntity<String> resetPassword(String email, String newPassword) {
        Optional<PasswordResetToken> tokenOpt = tokenDao.findTopByEmailOrderByExpiryTimeDesc(email);

        if (tokenOpt.isEmpty() || tokenOpt.get().getUsed()) {
            return ResponseEntity.badRequest().body("OTP not varified");
        }
        AuthUser user = authUserDao.findByEmail(email).orElseThrow();
        user.setPassword(passwordEncoder.encode(newPassword));
        authUserDao.save(user);

        PasswordResetToken token = tokenOpt.get();
        token.setUsed(true);
        tokenDao.save(token);

        return ResponseEntity.ok("Password reset successful");
    }

    @Override
    public ResponseEntity<?> refreshToken(String refreshToken) {
        rateLimitUtil.validate(refreshToken);

        RefreshToken token = refreshTokenDao
                .findByTokenAndRevokedFalse(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (token.getExpiryTime().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Refresh token expired");
        }

        AuthUser user = authUserDao.findByEmail(token.getEmail())
                .orElseThrow();

        rateLimitUtil.reset(refreshToken);

        String newAccessToken = jwtUtil.generateAccessToken(
                user.getId(),
                user.getEmail(),
                user.getRole());

        return ResponseEntity.ok(Map.of("AccessToken", newAccessToken));
    }

    @Override
    public ResponseEntity<?> logout(String refreshToken) {
        RefreshToken token = refreshTokenDao
                .findByTokenAndRevokedFalse(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        token.setRevoked(true);
        refreshTokenDao.save(token);
        return ResponseEntity.ok("Logged out successFully");
    }

    @Override
    public ResponseEntity<?> verifyEmail(String email, String otp) {
        rateLimitUtil.validate(email);
        var tokenOpt = emailVerificationDao.findByEmailAndOtpAndUsedFalse(email, otp);
        if (tokenOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }
        EmailVerificationToken token = tokenOpt.get();
        if (token.getExpiryTime().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("OTP expired");
        }

        AuthUser user = authUserDao.findByEmail(email).orElseThrow();
        user.setEmailVerified(true);
        authUserDao.save(user);

        token.setUsed(true);
        emailVerificationDao.save(token);

        rateLimitUtil.reset(email);

        return ResponseEntity.ok("Email verified successfully.");
    }

    @Override
    public ResponseEntity<?> getActiveSessions(String token) {

        String jwt = token.substring(7);
        String email= jwtUtil.extractUsername(jwt);
        return ResponseEntity.ok(refreshTokenDao.findByTokenAndRevokedFalse(email));
    }

    @Override
    public ResponseEntity<?> logoutAllDevices(String authorizationHeader) {
        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer")){
            return ResponseEntity.badRequest().body("Invalid Authorization header");
        }
        String token = authorizationHeader.substring(7);
        String email = jwtUtil.extractUsername(token);

        List<RefreshToken> tokens = refreshTokenDao.findByEmailAndRevokedFalse(email);

        if(tokens.isEmpty()){
            return ResponseEntity.ok("No active sessions found");
        }

        tokens.forEach(t -> t.setRevoked(true));
        refreshTokenDao.saveAll(tokens);
        return ResponseEntity.ok("Logged out from all all devices successfully");
    }


}