package com.servicehub.booking_service.dao;

import com.servicehub.booking_service.POJO.BookingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingHistoryDao extends JpaRepository<BookingHistory, Long> {

    List<BookingHistory> findByBookingIdOrderByChangedAtAsc(Long bookingId);
}
