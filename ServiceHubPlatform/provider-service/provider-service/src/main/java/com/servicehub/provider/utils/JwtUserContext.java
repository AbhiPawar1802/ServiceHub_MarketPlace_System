package com.servicehub.provider.utils;

public class JwtUserContext {

    private static final ThreadLocal<Long> authUserId = new ThreadLocal<>();

    private static final ThreadLocal<String> role = new ThreadLocal<>();

    public static void setAuthUserId(Long id){
        authUserId.set(id);
    }

    public static Long getAuthUserId(){
        return authUserId.get();
    }

    public static void setRole(String r){
        role.set(r);
    }

    public static String getRole(){
        return role.get();
    }

    public static void clear(){
        authUserId.remove();
        role.remove();
    }
}
