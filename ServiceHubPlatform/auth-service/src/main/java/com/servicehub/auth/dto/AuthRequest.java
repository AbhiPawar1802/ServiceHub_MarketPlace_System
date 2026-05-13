package com.servicehub.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthRequest {

    private String accessToken;
    private String refreshToken;
    private String role;

}
