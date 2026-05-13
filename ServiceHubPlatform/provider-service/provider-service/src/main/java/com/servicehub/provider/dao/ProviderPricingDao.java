package com.servicehub.provider.dao;

import com.servicehub.provider.POJO.ProviderPricing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderPricingDao extends JpaRepository<ProviderPricing, Long> {

    List<ProviderPricing> findByProviderId(Long providerId);

    List<ProviderPricing> findByServiceCategory(String serviceCategory);
}
