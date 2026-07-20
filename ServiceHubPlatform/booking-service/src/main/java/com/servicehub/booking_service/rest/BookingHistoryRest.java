package com.servicehub.booking_service.rest;

import com.servicehub.booking_service.POJO.BookingHistory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BookingHistoryRest {

    @GetMapping("/{bookingId}")
    ResponseEntity<List<BookingHistory>> getBookingHistory(@PathVariable Long bookingId);

}
