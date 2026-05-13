package com.servicehub.provider.dao;

import com.servicehub.provider.POJO.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProviderDao extends JpaRepository<Provider, Long> {

    Optional<Provider> findByAuthUserId(Long authUserId);

    @Query(value = """
SELECT
    p.id,
    p.name,
    p.rating,
    p.experience,
    pr.service_category,
    pr.base_visit_charge,
    pr.hourly_charge,
    (
        6371 * acos(
            cos(radians(:lat)) * cos(radians(p.latitude)) *
            cos(radians(p.longitude) - radians(:lon)) +
            sin(radians(:lat)) * sin(radians(p.latitude))
        )
    ) AS distance
FROM providers p
JOIN provider_pricing pr ON p.id = pr.provider_id
 WHERE LOWER(pr.service_category) = LOWER(:category) AND p.status = 'ACTIVE'
AND (
    6371 * acos(
        cos(radians(:lat)) * cos(radians(p.latitude)) *
        cos(radians(p.longitude) - radians(:lon)) +
        sin(radians(:lat)) * sin(radians(p.latitude))
    )
) <= p.service_radius
ORDER BY distance ASC
LIMIT 20
""", nativeQuery = true)
    List<Object[]> searchProviders(
            @Param("category") String category,
            @Param("lat") Double lat,
            @Param("lon") Double lon
    );

}
