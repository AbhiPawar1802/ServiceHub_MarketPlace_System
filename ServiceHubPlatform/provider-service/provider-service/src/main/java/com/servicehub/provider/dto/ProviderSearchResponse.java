package com.servicehub.provider.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProviderSearchResponse {

    private Long providerId;
    private String name;
    private Double rating;
    private Integer experienceYears;
    private String serviceCategory;
    private Double baseVisitCharge;
    private Double hourlyCharge;
    private Double distanceKm;

}
