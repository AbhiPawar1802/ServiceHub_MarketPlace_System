package com.servicehub.provider.serviceImpl;

import com.servicehub.provider.POJO.Provider;
import com.servicehub.provider.POJO.ProviderPricing;
import com.servicehub.provider.dao.ProviderDao;
import com.servicehub.provider.dao.ProviderPricingDao;
import com.servicehub.provider.dto.CreatePricingRequest;
import com.servicehub.provider.dto.PricingResponse;
import com.servicehub.provider.service.PricingService;
import com.servicehub.provider.utils.JwtUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PricingServiceImpl implements PricingService {

    private final ProviderPricingDao pricingDao;
    private final ProviderDao providerDao;

    @Override
    public PricingResponse addPricing(CreatePricingRequest request) {
        Long authUserId = JwtUserContext.getAuthUserId();

        Provider provider = providerDao.findByAuthUserId(authUserId)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        ProviderPricing pricing = ProviderPricing.builder()
                .providerId(provider.getId())
                .serviceCategory(request.getServiceCategory())
                .baseVisitCharge(request.getBaseVisitCharge())
                .hourlyCharge(request.getHourlyCharge())
                .minEstimatedCharge(request.getMinEstimatedCharge())
                .maxEstimatedCharge(request.getMaxEstimatedCharge())
                .inspectionRequired(request.getInspectionRequired())
                .build();

        pricingDao.save(pricing);

        return map(pricing);
    }

    @Override
    public List<PricingResponse> getMyPricing() {

        Long authUserId = JwtUserContext.getAuthUserId();
        Provider provider = providerDao.findByAuthUserId(authUserId)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        return pricingDao.findByProviderId(provider.getId())
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private PricingResponse map(ProviderPricing p) {
        return PricingResponse.builder()
                .id(p.getId())
                .serviceCategory(p.getServiceCategory())
                .baseVisitCharge(p.getBaseVisitCharge())
                .hourlyCharge(p.getHourlyCharge())
                .minEstimatedCharge(p.getMinEstimatedCharge())
                .maxEstimatedCharge(p.getMaxEstimatedCharge())
                .inspectionRequired(p.getInspectionRequired())
                .build();
    }
}
