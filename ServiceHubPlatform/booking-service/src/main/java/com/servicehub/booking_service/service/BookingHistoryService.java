package com.servicehub.booking_service.service;

import com.servicehub.booking_service.POJO.BookingHistory;
import com.servicehub.booking_service.utils.BookingStatus;

import java.util.List;

public interface BookingHistoryService {

    void saveHistory(Long bookingId,
                     BookingStatus oldStatus,
                     BookingStatus newStatus,
                     Long changedBy,
                     String remarks);

    List<BookingHistory> getBookingHistory(Long bookingId);
}
