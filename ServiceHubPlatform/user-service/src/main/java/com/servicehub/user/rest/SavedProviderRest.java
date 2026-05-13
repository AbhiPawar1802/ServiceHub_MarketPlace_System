package com.servicehub.user.rest;

import com.servicehub.user.dto.SaveProviderRequest;
import com.servicehub.user.dto.SavedProviderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface SavedProviderRest {

    @PostMapping("/saved-providers")
    ResponseEntity<String> saveProvider(@RequestBody SaveProviderRequest request);

    @GetMapping("/saved-providers")
    ResponseEntity<List<SavedProviderResponse>> getSavedProviders();

    @DeleteMapping("/saved-providers/{providerId}")
    ResponseEntity<String> removeProvider(@PathVariable String providerId);
}
