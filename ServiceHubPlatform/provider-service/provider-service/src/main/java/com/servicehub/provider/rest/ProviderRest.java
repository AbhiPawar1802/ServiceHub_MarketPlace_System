package com.servicehub.provider.rest;

import com.servicehub.provider.dto.ProviderResponse;
import com.servicehub.provider.dto.RegisterProviderRequest;
import com.servicehub.provider.dto.UpdateLocationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ProviderRest {

    @PostMapping("/register")
    ResponseEntity<ProviderResponse> register(@RequestBody RegisterProviderRequest request);

    @GetMapping("/me")
    ResponseEntity<ProviderResponse> getMyProfile();

    @PutMapping("/location")
    ResponseEntity<?> updateLocation(@RequestBody UpdateLocationRequest request);
}
