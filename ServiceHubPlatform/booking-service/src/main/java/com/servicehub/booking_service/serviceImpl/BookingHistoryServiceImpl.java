package com.servicehub.booking_service.serviceImpl;

import com.servicehub.booking_service.POJO.BookingHistory;
import com.servicehub.booking_service.dao.BookingHistoryDao;
import com.servicehub.booking_service.service.BookingHistoryService;
import com.servicehub.booking_service.utils.BookingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingHistoryServiceImpl implements BookingHistoryService {

    @Autowired
    private BookingHistoryDao bookingHistoryDao;

    @Override
    public void saveHistory(Long bookingId, BookingStatus oldStatus, BookingStatus newStatus, Long changedBy, String remarks) {

        try{

            BookingHistory history = BookingHistory.builder()
                    .bookingId(bookingId)
                    .oldStatus(oldStatus)
                    .newStatus(newStatus)
                    .changedAt(LocalDateTime.now())
                    .changedBy(changedBy)
                    .remarks(remarks)
                    .build();

            bookingHistoryDao.save(history);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<BookingHistory> getBookingHistory(Long bookingId) {

        return bookingHistoryDao.findByBookingIdOrderByChangedAtAsc(bookingId);

    }
}
