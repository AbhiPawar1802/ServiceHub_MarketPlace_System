package com.servicehub.booking_service.restImpl;

import com.servicehub.booking_service.POJO.Booking;
import com.servicehub.booking_service.dto.BookingRequestDto;
import com.servicehub.booking_service.rest.BookingRest;
import com.servicehub.booking_service.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/bookings")
public class BookingRestImpl implements BookingRest {

    @Autowired
    private BookingService bookingService;


    @Override
    public ResponseEntity<?> createBooking(@RequestBody BookingRequestDto dto) {
        return bookingService.createBooking(dto);
    }

    @Override
    public ResponseEntity<?> getUserBooking(@RequestParam("userId") Long userId) {
        return bookingService.getUserBooking(userId);
    }

    @Override
    public ResponseEntity<?> getProviderBooking(Long providerId) {
        return bookingService.getProviderBooking(providerId);
    }

    @Override
    public ResponseEntity<?> updateStatus(Long id, String status) {
        return bookingService.updateStatus(id, status);
    }

    @Override
    public ResponseEntity<?> cancelBooking(Long id) {
        return bookingService.cancelBooking(id);
    }
}