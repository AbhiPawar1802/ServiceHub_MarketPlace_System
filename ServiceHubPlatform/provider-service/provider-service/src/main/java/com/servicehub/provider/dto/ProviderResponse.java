package com.servicehub.provider.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProviderResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String bio;
    private Integer experienceYears;
    private Double serviceRadiusKm;
    private String status;
//    private Double latitude;
//    private Double longitude;

}
