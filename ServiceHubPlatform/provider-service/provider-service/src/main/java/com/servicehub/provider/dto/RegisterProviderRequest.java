package com.servicehub.provider.dto;

import lombok.Data;

@Data
public class RegisterProviderRequest {

    private String name;
    private String email;
    private String phone;
    private String bio;
    private Integer experienceYears;
    private Double serviceRadiusKm;

}
