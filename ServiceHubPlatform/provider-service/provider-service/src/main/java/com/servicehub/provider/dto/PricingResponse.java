package com.servicehub.provider.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PricingResponse {

    private Long id;
    private String serviceCategory;
    private Double baseVisitCharge;
    private Double hourlyCharge;
    private Double minEstimatedCharge;
    private Double maxEstimatedCharge;
    private Boolean inspectionRequired;

}
