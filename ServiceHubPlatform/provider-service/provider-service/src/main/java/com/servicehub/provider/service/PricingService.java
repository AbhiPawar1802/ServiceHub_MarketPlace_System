package com.servicehub.provider.service;

import com.servicehub.provider.dto.CreatePricingRequest;
import com.servicehub.provider.dto.PricingResponse;

import java.util.List;

public interface PricingService {

    PricingResponse addPricing(CreatePricingRequest request);

    List<PricingResponse> getMyPricing();
}
