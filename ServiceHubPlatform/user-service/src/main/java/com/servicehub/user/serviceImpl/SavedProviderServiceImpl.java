package com.servicehub.user.serviceImpl;

import com.servicehub.user.POJO.SavedProvider;
import com.servicehub.user.dao.SavedProviderDao;
import com.servicehub.user.dto.SaveProviderRequest;
import com.servicehub.user.dto.SavedProviderResponse;
import com.servicehub.user.service.SavedProviderService;
import com.servicehub.user.utils.JwtUserContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SavedProviderServiceImpl implements SavedProviderService {

    private final SavedProviderDao dao;

    @Override
    public void saveProvider(SaveProviderRequest request) {
        Long authUserId = JwtUserContext.getAuthUserId();

        dao.findByAuthUserIdAndProviderId(authUserId, request.getProviderId())
                .ifPresent(existing -> {
                    throw new RuntimeException("Provider already saved");
                });

        SavedProvider provider = SavedProvider.builder()
                .authUserId(authUserId)
                .providerId(request.getProviderId())
                .providerName(request.getProviderName())
                .build();

        dao.save(provider);
    }

    @Override
    public List<SavedProviderResponse> getSavedProviders() {

        Long authUserId = JwtUserContext.getAuthUserId();

        return dao.findByAuthUserId(authUserId)
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void removeProvider(String providerId) {

        Long authUserId = JwtUserContext.getAuthUserId();

        dao.deleteByAuthUserIdAndProviderId(authUserId, providerId);

    }

    private SavedProviderResponse map(SavedProvider provider){
        return SavedProviderResponse.builder()
                .providerId(provider.getProviderId())
                .providerName(provider.getProviderName())
                .savedAt(provider.getSavedAt())
                .build();
    }
}
