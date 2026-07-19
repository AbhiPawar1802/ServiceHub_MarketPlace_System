package com.servicehub.booking_service.dto;

import lombok.Data;

@Data
public class ProviderServiceResponse {

    private Long providerId;
    private String name;
    private Double rating;
    private Integer experienceYears;
    private String serviceCategory;
    private Double baseVisitCharge;
    private Double hourlyCharge;
    private Double distanceKm;

}
