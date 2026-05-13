package com.servicehub.provider.restImpl;

import com.servicehub.provider.dto.CreatePricingRequest;
import com.servicehub.provider.dto.PricingResponse;
import com.servicehub.provider.rest.ProviderPricingRest;
import com.servicehub.provider.service.PricingService;
import com.servicehub.provider.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderPricingRestImpl implements ProviderPricingRest {

    private final PricingService pricingService;

    @Override
    public ResponseEntity<PricingResponse> addPricing(CreatePricingRequest request) {
        return ResponseEntity.ok(pricingService.addPricing(request));
    }

    @Override
    public ResponseEntity<List<PricingResponse>> getMyPricing() {
        return ResponseEntity.ok(pricingService.getMyPricing());
    }
}
