package com.servicehub.booking_service.serviceImpl;

import com.servicehub.booking_service.POJO.Booking;
import com.servicehub.booking_service.POJO.BookingHistory;
import com.servicehub.booking_service.dao.BookingDao;
import com.servicehub.booking_service.dao.BookingHistoryDao;
import com.servicehub.booking_service.dto.BookingRequestDto;
import com.servicehub.booking_service.dto.ProviderServiceResponse;
import com.servicehub.booking_service.rest.ProviderFeignClientRest;
import com.servicehub.booking_service.service.BookingService;
import com.servicehub.booking_service.utils.BookingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private ProviderFeignClientRest providerFeignClientRest;

    @Autowired
    private BookingHistoryDao bookingHistoryDao;


    @Override
    public ResponseEntity<?> createBooking(Long userId, BookingRequestDto dto) {
        try {
            ProviderServiceResponse providerResponse = providerFeignClientRest.autoAssignProvider(
                    dto.getServiceCategory(),
                    dto.getLatitude(),
                    dto.getLongitude()
            );

            if (providerResponse == null) {
                return new ResponseEntity<>("No provider available", HttpStatus.BAD_REQUEST);
            }

            Booking booking = Booking.builder()
                    .userId(userId)
                    .providerId(providerResponse.getProviderId())
                    .serviceCategory(dto.getServiceCategory())
                    .latitude(dto.getLatitude())
                    .longitude(dto.getLongitude())
                    .status(BookingStatus.REQUESTED)
                    .estimatedPrice(providerResponse.getBaseVisitCharge())
                    .createdAt(LocalDateTime.now())
                    .build();

            bookingDao.save(booking);

            return new ResponseEntity<>(booking, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getUserBooking(Long userId) {
        try {
            System.out.println("Searching bookings for userId = " + userId);
            List<Booking> list = bookingDao.findByUserId(userId);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getProviderBooking(Long providerId) {
        try {
            List<Booking> list = bookingDao.findByProviderId(providerId);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> updateStatus(Long bookingId, String status) {
        try {
            Booking booking = bookingDao.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));

            if (booking.getStatus() == BookingStatus.CANCELLED || booking.getStatus() == BookingStatus.COMPLETED) {
                return new ResponseEntity<>("Booking cannot be updated", HttpStatus.BAD_REQUEST);
            }

            try {
                booking.setStatus(BookingStatus.valueOf(status.toUpperCase()));
            } catch (Exception e) {
                return new ResponseEntity<>("Invalid status", HttpStatus.BAD_REQUEST);
            }
            bookingDao.save(booking);
            return new ResponseEntity<>("Booking status saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> cancelBooking(Long bookingId) {
        try {
            Booking booking = bookingDao.findById(bookingId)
                    .orElseThrow(() -> new RuntimeException("Booking not found"));

            //Cannot cancel completed booking
            if (booking.getStatus() == BookingStatus.COMPLETED) {
                return new ResponseEntity<>("Completed bookings cannot be cancelled", HttpStatus.BAD_REQUEST);
            }

            //Prevent cancelling twice
            if (booking.getStatus() == BookingStatus.CANCELLED) {
                return new ResponseEntity<>("Booking already cancelled", HttpStatus.BAD_REQUEST);
            }

            booking.setStatus(BookingStatus.CANCELLED);
            bookingDao.save(booking);

            return new ResponseEntity<>("Booking cancelled", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
