package com.servicehub.booking_service.restImpl;

import com.servicehub.booking_service.POJO.BookingHistory;
import com.servicehub.booking_service.dao.BookingHistoryDao;
import com.servicehub.booking_service.rest.BookingHistoryRest;
import com.servicehub.booking_service.service.BookingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booking-history")
public class BookingHistoryRestImpl implements BookingHistoryRest {

    @Autowired
    private BookingHistoryDao bookingHistoryDao;

    @Autowired
    private BookingHistoryService bookingServiceHistory;

    @Override
    public ResponseEntity<List<BookingHistory>> getBookingHistory(Long bookingId) {

        return ResponseEntity.ok(bookingServiceHistory.getBookingHistory(bookingId));

    }

}
