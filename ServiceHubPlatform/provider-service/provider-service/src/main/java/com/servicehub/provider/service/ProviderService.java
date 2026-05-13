package com.servicehub.provider.service;

import com.servicehub.provider.dto.ProviderResponse;
import com.servicehub.provider.dto.RegisterProviderRequest;
import com.servicehub.provider.dto.UpdateLocationRequest;

public interface ProviderService {

    ProviderResponse register(RegisterProviderRequest request);

    ProviderResponse getMyProfile();

    void updateLocation(UpdateLocationRequest request);
}
