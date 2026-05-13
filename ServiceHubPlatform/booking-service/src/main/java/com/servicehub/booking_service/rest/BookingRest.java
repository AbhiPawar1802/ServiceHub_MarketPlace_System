package com.servicehub.booking_service.rest;

import com.servicehub.booking_service.dto.BookingRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface BookingRest {

    @PostMapping("/create")
    ResponseEntity<?> createBooking(@RequestBody BookingRequestDto dto);

    @GetMapping("/user-booking")
    ResponseEntity<?> getUserBooking(@RequestParam("userId") Long userId);

    @GetMapping("/provider-booking")
    ResponseEntity<?> getProviderBooking(@RequestParam Long providerId);

    @PutMapping("/{id}/status")
    ResponseEntity<?> updateStatus(@PathVariable Long id,@RequestParam String status);

    @DeleteMapping("/{id}")
    ResponseEntity<?> cancelBooking(@PathVariable Long id);

}
