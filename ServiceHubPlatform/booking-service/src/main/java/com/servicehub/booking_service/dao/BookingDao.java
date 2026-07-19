package com.servicehub.booking_service.dao;

import com.servicehub.booking_service.POJO.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingDao extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long userId);

    List<Booking> findByProviderId(Long providerId);

    List<Booking> findByUserIdOrderByCreatedAtDesc(Long userId);
}
