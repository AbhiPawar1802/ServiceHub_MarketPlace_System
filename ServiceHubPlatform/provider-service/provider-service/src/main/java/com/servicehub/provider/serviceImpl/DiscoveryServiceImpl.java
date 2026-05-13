package com.servicehub.provider.serviceImpl;

import com.servicehub.provider.POJO.Provider;
import com.servicehub.provider.POJO.ProviderPricing;
import com.servicehub.provider.dao.ProviderDao;
import com.servicehub.provider.dao.ProviderPricingDao;
import com.servicehub.provider.dto.ProviderSearchResponse;
import com.servicehub.provider.service.DiscoveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscoveryServiceImpl implements DiscoveryService {

    private final ProviderDao providerDao;

    @Override
    public List<ProviderSearchResponse> search(String serviceCategory, Double latitude, Double longitude) {
        List<Object[]> rows = providerDao.searchProviders(serviceCategory, latitude, longitude);

        List<ProviderSearchResponse> results = new ArrayList<>();

        for(Object[] row : rows){
            results.add(
                    ProviderSearchResponse.builder()
                            .providerId(((Number) row[0]).longValue())
                            .name((String) row[1])
                            .rating(row[2] != null ? ((Number) row[2]).doubleValue() : 0.0)
                            .experienceYears(row[3] != null ? ((Number) row[3]).intValue() : 0)
                            .serviceCategory((String) row[4])
                            .baseVisitCharge(row[5] != null ? ((Number) row[5]).doubleValue() : 0.0)
                            .hourlyCharge(row[6] != null ? ((Number) row[6]).doubleValue() : 0.0)
                            .distanceKm(row[7] != null ? ((Number) row[7]).doubleValue() : 0.0)
                            .build()
            );
        }
        return results;

    }

    @Override
    public ProviderSearchResponse autoAssign(String serviceCategory, Double latitude, Double longitude) {
        List<ProviderSearchResponse> providers = search(serviceCategory, latitude, longitude);

        if(providers.isEmpty()){
            throw new RuntimeException("No providers available nearby");
        }
        return providers.stream()
                .sorted((p1, p2) -> {
                    double score1 = p1.getDistanceKm() - (p1.getRating() * 0.5);
                    double score2 = p2.getDistanceKm() - (p2.getRating() * 0.5);
                    return Double.compare(score1, score2);
        })
                .findFirst()
                .get();
    }

    private Double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2){
        final int r = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                +Math.cos(Math.toRadians(lat1))
                *Math.cos(Math.toRadians(lat2))
                *Math.sin(lonDistance / 2)
                *Math.sin(lonDistance / 2);

        double c = Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return r * c;
    }
}
