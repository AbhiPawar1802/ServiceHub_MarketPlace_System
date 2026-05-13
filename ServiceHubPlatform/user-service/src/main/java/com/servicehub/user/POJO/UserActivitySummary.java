package com.servicehub.user.POJO;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "userActivitySummary")
@Data
public class UserActivitySummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "auth_user_id", nullable = false, unique = true)
    private Long authUserId;

    @Column(name = "total_booking")
    private Long totalBooking;

    @Column(name = "completed_booking")
    private Long completedBooking;

    @Column(name = "cancelled_bookings")
    private Long cancelledBookings;

    @Column(name = "total_spent")
    private Double totalSpent;

    @Column(name = "saved_providers_count")
    private Long savedProvidersCount;

    @Column(name = "address_count")
    private Long addressCount;

    @Column(name = "last_booking_date")
    private LocalDateTime lastBookingDate;

    @Column(name = "most_used_service_category")
    private String mostUsedServiceCategory;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "lastLogin")
    private LocalDateTime lastLogin;

    @PrePersist
    @PreUpdate
    public void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

}
