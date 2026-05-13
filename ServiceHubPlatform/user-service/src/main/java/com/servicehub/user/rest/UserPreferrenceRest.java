package com.servicehub.user.rest;

import com.servicehub.user.dto.UserPreferenceRequest;
import com.servicehub.user.dto.UserPreferenceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public interface UserPreferrenceRest {

    @GetMapping("/preferences")
    ResponseEntity<UserPreferenceResponse> getPreferrences();

    @PutMapping("/preferrences")
    ResponseEntity<UserPreferenceResponse> updatePreferences(@RequestBody UserPreferenceRequest request);
}
