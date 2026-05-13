package com.servicehub.booking_service.dto;

import lombok.Data;

@Data
public class BookingRequestDto {

    private Long userId;
    private String serviceCategory;
    private Double latitude;
    private Double longitude;
}
