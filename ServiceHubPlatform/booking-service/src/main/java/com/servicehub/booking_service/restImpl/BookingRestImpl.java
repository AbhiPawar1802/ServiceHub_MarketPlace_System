package com.servicehub.booking_service.restImpl;

import com.servicehub.booking_service.POJO.Booking;
import com.servicehub.booking_service.dto.BookingRequestDto;
import com.servicehub.booking_service.rest.BookingRest;
import com.servicehub.booking_service.service.BookingService;
import com.servicehub.booking_service.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/bookings")
public class BookingRestImpl implements BookingRest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public ResponseEntity<?> createBooking(String token, @RequestBody BookingRequestDto dto) {

        String jwtToken = token.substring(7);
        Long userId = jwtUtil.extractUserId(jwtToken);
        System.out.println("JWT User ID = "+ userId);
        return bookingService.createBooking(dto);
    }

    @Override
    public ResponseEntity<?> getUserBooking(@RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        Long userId = jwtUtil.extractUserId(jwtToken);

        System.out.println("JWT User ID ="+ userId);
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