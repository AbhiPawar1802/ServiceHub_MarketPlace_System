package com.servicehub.user.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class JwtUserContext {


    private static Jwt jwt() {
        return (Jwt) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public static Long getAuthUserId() {
        Number id = jwt().getClaim("authUserId");
        return id != null ? id.longValue() : null;
    }

    public static String getEmail() {
        return jwt().getSubject();
    }

    public static String getRole() {
        return jwt().getClaim("role");
    }
}
