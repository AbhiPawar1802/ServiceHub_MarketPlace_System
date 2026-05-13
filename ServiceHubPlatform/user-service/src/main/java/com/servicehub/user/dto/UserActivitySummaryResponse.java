package com.servicehub.user.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserActivitySummaryResponse {

    private Long totalBooking;
    private Long completedBooking;
    private Long cancelledBookings;
    private Double totalSpent;
    private Long savedProviderCount;
    private Long addressCount;
    private LocalDateTime lastBookingDate;
    private String mostUsedServiceCategory;
    private LocalDateTime lastLogin;

}
