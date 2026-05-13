package com.servicehub.user.restImpl;

import com.servicehub.user.dto.UserPreferenceRequest;
import com.servicehub.user.dto.UserPreferenceResponse;
import com.servicehub.user.rest.UserPreferrenceRest;
import com.servicehub.user.service.UserPreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserPreferencesRestImpl implements UserPreferrenceRest {

    private final UserPreferenceService service;

    @Override
    public ResponseEntity<UserPreferenceResponse> getPreferrences() {
        return ResponseEntity.ok(service.getPreference());
    }

    @Override
    public ResponseEntity<UserPreferenceResponse> updatePreferences(UserPreferenceRequest request) {
        return ResponseEntity.ok(service.updatePreferences(request));
    }
}
