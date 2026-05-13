package com.servicehub.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String name;
    private int phone;
    private String email;
    private String password;

    private String role;
}
