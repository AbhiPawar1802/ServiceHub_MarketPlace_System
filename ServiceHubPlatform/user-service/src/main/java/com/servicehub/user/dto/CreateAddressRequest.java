package com.servicehub.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAddressRequest {

    @NotBlank
    private String label;

    @NotBlank
    private String line1;

    @NotBlank
    private String line2;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String pincode;

    private Double latitude;
    private Double longitude;

    private Boolean isDefault;

}
