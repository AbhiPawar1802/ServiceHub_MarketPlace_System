package com.servicehub.provider.serviceImpl;

import com.servicehub.provider.POJO.Provider;
import com.servicehub.provider.dao.ProviderDao;
import com.servicehub.provider.dto.ProviderResponse;
import com.servicehub.provider.dto.RegisterProviderRequest;
import com.servicehub.provider.dto.UpdateLocationRequest;
import com.servicehub.provider.service.ProviderService;
import com.servicehub.provider.utils.JwtUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private final ProviderDao providerDao;

    @Override
    public ProviderResponse register(RegisterProviderRequest request) {

        Long authUserId = JwtUserContext.getAuthUserId();

        if(providerDao.findByAuthUserId(authUserId).isPresent()){
            throw new RuntimeException("Provider already registered");
        }

        Provider provider = Provider.builder()
                .authUserId(authUserId)
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .bio(request.getBio())
                .experienceYears(request.getExperienceYears())
                .serviceRadiusKm(request.getServiceRadiusKm())
                .rating(0.0)
                .totalJobs(0)
                .status("PENDING")
                .verified(false)
                .build();

        providerDao.save(provider);

        return map(provider);
    }

    @Override
    public ProviderResponse getMyProfile() {

        Long authUserId = JwtUserContext.getAuthUserId();
        Provider provider = providerDao.findByAuthUserId(authUserId)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        return map(provider);
    }

    @Override
    public void updateLocation(UpdateLocationRequest request) {
        Long authUserId = JwtUserContext.getAuthUserId();

        Provider provider = providerDao.findByAuthUserId(authUserId)
                .orElseThrow(() -> new RuntimeException("Provider not found."));

        provider.setLatitude(request.getLatitude());
        provider.setLongitude(request.getLongitude());

        providerDao.save(provider);
    }

    private ProviderResponse map(Provider provider){
        return ProviderResponse.builder()
                .id(provider.getId())
                .name(provider.getName())
                .email(provider.getEmail())
                .phone(provider.getPhone())
                .bio(provider.getBio())
                .experienceYears(provider.getExperienceYears())
                .serviceRadiusKm(provider.getServiceRadiusKm())
                .status(provider.getStatus())
                .build();
    }
}
