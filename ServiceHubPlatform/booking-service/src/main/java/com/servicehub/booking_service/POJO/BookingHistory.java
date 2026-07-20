package com.servicehub.booking_service.POJO;

import com.servicehub.booking_service.utils.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking_history")
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingHistory {

    @Id
    @GeneratedValue
    private Long id;

    private Long bookingId;

    @Enumerated(EnumType.STRING)
    private BookingStatus oldStatus;

    @Enumerated(EnumType.STRING)
    private BookingStatus newStatus;

    private LocalDateTime changedAt;

    private Long changedBy;

    private String remarks;

}
