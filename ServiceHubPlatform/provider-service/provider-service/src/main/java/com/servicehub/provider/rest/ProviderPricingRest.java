package com.servicehub.provider.rest;

import com.servicehub.provider.dto.CreatePricingRequest;
import com.servicehub.provider.dto.PricingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProviderPricingRest {

    @PostMapping("/pricing")
    ResponseEntity<PricingResponse> addPricing(@RequestBody CreatePricingRequest request);

    @GetMapping("/pricing")
    ResponseEntity<List<PricingResponse>> getMyPricing();
}
