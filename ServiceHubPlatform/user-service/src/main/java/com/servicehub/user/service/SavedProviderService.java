package com.servicehub.user.service;

import com.servicehub.user.dto.SaveProviderRequest;
import com.servicehub.user.dto.SavedProviderResponse;

import java.util.List;

public interface SavedProviderService {

    void saveProvider(SaveProviderRequest request);

    List<SavedProviderResponse> getSavedProviders();

    void removeProvider(String providerId);
}
