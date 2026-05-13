package com.servicehub.booking_service.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "provider-service", url = "http://localhost:8083")
public interface ProviderFeignClientRest {

    @GetMapping("/provider/auto-assign")
    Long autoAssignProvider(@RequestParam String serviceCategory,
                            @RequestParam Double latitude,
                            @RequestParam Double longitude);
}
