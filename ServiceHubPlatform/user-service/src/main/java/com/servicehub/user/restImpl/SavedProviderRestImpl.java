package com.servicehub.user.restImpl;

import com.servicehub.user.dto.SaveProviderRequest;
import com.servicehub.user.dto.SavedProviderResponse;
import com.servicehub.user.rest.SavedProviderRest;
import com.servicehub.user.service.SavedProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class SavedProviderRestImpl implements SavedProviderRest {

    private final SavedProviderService service;

    @Override
    public ResponseEntity<String> saveProvider(SaveProviderRequest request) {
        service.saveProvider(request);
        return ResponseEntity.ok("Provider saved successfully");
    }

    @Override
    public ResponseEntity<List<SavedProviderResponse>> getSavedProviders() {
        return ResponseEntity.ok(service.getSavedProviders());
    }

    @Override
    public ResponseEntity<String> removeProvider(String providerId) {
        service.removeProvider(providerId);
        return ResponseEntity.ok("Provider removed successfully");
    }
}
