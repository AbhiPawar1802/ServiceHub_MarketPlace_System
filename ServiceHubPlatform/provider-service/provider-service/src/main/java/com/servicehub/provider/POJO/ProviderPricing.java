package com.servicehub.provider.POJO;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "provider_pricing")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProviderPricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider_id", unique = true)
    private Long providerId;

    @Column(name = "service_category")
    private String serviceCategory;

    @Column(name = "baseVisitCharge")
    private Double baseVisitCharge;

    @Column(name = "hourly_charge")
    private Double hourlyCharge;

    @Column(name = "min_estimated-charge")
    private Double minEstimatedCharge;

    @Column(name = "max_estimated-charge")
    private Double maxEstimatedCharge;

    @Column(name = "inspection_required")
    private Boolean inspectionRequired;
}
