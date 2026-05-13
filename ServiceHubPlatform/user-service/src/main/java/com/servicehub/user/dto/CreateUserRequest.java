package com.servicehub.user.dto;
import lombok.Data;

@Data
public class CreateUserRequest {

    private Long authUserId;
    private String email;
    private String name;
    private String phone;

}
