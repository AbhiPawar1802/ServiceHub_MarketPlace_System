package com.servicehub.provider.restImpl;

import com.servicehub.provider.dto.ProviderSearchResponse;
import com.servicehub.provider.rest.DiscoveryRest;
import com.servicehub.provider.service.DiscoveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class DiscoveryRestImpl implements DiscoveryRest {

    private final DiscoveryService discoveryService;

    @Override
    public ResponseEntity<List<ProviderSearchResponse>> searchProviders(String service, Double lat, Double lon) {
        return ResponseEntity.ok(discoveryService.search(service, lat, lon));
    }

    @Override
    public ResponseEntity<ProviderSearchResponse> autoAssign(String serviceCategory, Double latitude, Double longitude) {
        return ResponseEntity.ok(discoveryService.autoAssign(serviceCategory, latitude, longitude));
    }
}
