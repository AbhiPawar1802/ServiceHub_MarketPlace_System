package com.servicehub.booking_service.POJO;

import com.servicehub.booking_service.utils.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EnableFeignClients
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long providerId;

    @Column(name = "serviceCategory")
    private String serviceCategory;

    @Column(name = "estimatedPrice")
    private Double estimatedPrice;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

}
