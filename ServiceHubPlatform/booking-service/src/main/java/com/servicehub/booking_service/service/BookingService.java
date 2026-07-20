package com.servicehub.booking_service.service;

import com.servicehub.booking_service.POJO.Booking;
import com.servicehub.booking_service.dto.BookingRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {

    ResponseEntity<?> createBooking(Long userId, BookingRequestDto dto);

    ResponseEntity<?> getUserBooking(Long userId);

    ResponseEntity<?> getProviderBooking(Long providerId);

    ResponseEntity<?> updateStatus(Long bookingId, String status);

    ResponseEntity<?> cancelBooking(Long bookingId);
}
