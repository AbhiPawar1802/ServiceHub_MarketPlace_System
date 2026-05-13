package com.servicehub.provider.service;

import com.servicehub.provider.dto.ProviderSearchResponse;

import java.util.List;

public interface DiscoveryService {

    List<ProviderSearchResponse> search(String serviceCategory, Double latitude, Double longitude);

    ProviderSearchResponse autoAssign(String serviceCategory, Double latitude, Double longitude);
}
