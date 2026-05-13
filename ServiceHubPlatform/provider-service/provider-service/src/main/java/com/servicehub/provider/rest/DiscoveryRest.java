package com.servicehub.provider.rest;

import com.servicehub.provider.dto.ProviderSearchResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface DiscoveryRest {

    @GetMapping("/search")
    ResponseEntity<List<ProviderSearchResponse>> searchProviders(@RequestParam String service,
                                                                 @RequestParam Double lat,
                                                                 @RequestParam Double lon);

    @GetMapping("/auto-assign")
    ResponseEntity<ProviderSearchResponse> autoAssign(
            @RequestParam String serviceCategory,
            @RequestParam Double latitude,
            @RequestParam Double longitude
    );
}
