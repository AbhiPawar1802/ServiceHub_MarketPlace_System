package com.servicehub.booking_service.rest;

import com.servicehub.booking_service.POJO.Booking;
import com.servicehub.booking_service.dto.BookingRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BookingRest {

    @PostMapping("/create")
    ResponseEntity<?> createBooking(@RequestHeader("Authorization") String token, @RequestBody BookingRequestDto dto);

    @GetMapping("/user-booking")
    ResponseEntity<?> getUserBooking(@RequestHeader("Authorization") String token);

    @GetMapping("/provider-booking")
    ResponseEntity<?> getProviderBooking(@RequestParam Long providerId);

    @PutMapping("/{id}/status")
    ResponseEntity<?> updateStatus(@PathVariable Long id,@RequestParam String status);

    @DeleteMapping("/{id}")
    ResponseEntity<?> cancelBooking(@PathVariable Long id);


}
