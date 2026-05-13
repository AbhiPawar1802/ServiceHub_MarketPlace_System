package com.servicehub.provider.dto;

import lombok.Data;

@Data
public class CreatePricingRequest {

    private String serviceCategory;
    private Double baseVisitCharge;
    private Double hourlyCharge;
    private Double minEstimatedCharge;
    private Double maxEstimatedCharge;
    private Boolean inspectionRequired;

}
