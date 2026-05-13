package com.servicehub.provider.restImpl;

import com.servicehub.provider.dto.ProviderResponse;
import com.servicehub.provider.dto.RegisterProviderRequest;
import com.servicehub.provider.dto.UpdateLocationRequest;
import com.servicehub.provider.rest.ProviderRest;
import com.servicehub.provider.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderRestImpl implements ProviderRest {

    private final ProviderService service;

    @Override
    public ResponseEntity<ProviderResponse> register(RegisterProviderRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @Override
    public ResponseEntity<ProviderResponse> getMyProfile() {
        return ResponseEntity.ok(service.getMyProfile());
    }

    @Override
    public ResponseEntity<?> updateLocation(UpdateLocationRequest request) {
        service.updateLocation(request);
        return ResponseEntity.ok("Location updated");
    }
}
