package com.servicehub.user.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.property.access.internal.PropertyAccessStrategyNoopImpl;

@Data
@Builder
public class AddressResponse {

    private Long id;
    private String label;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String pincode;
    private Double latitude;
    private Double longitude;
    private Boolean isDefault;

}
