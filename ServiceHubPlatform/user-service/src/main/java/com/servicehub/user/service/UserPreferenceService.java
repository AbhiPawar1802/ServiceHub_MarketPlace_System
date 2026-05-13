package com.servicehub.user.service;

import com.servicehub.user.dto.UserPreferenceRequest;
import com.servicehub.user.dto.UserPreferenceResponse;

public interface UserPreferenceService {

    UserPreferenceResponse getPreference();

    UserPreferenceResponse updatePreferences(UserPreferenceRequest request);
}
