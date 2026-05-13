package com.servicehub.booking_service.serviceImpl;

import com.servicehub.booking_service.POJO.Booking;
import com.servicehub.booking_service.dao.BookingDao;
import com.servicehub.booking_service.dto.BookingRequestDto;
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


    @Override
    public ResponseEntity<?> createBooking(BookingRequestDto dto) {
        try{
            Long providerId = providerFeignClientRest.autoAssignProvider(
                    dto.getServiceCategory(),
                    dto.getLatitude(),
                    dto.getLatitude()
            );

            if(providerId == null){
                return new ResponseEntity<>("No provider available", HttpStatus.BAD_REQUEST);
            }

            Booking booking = Booking.builder()
                    .userId(dto.getUserId())
                    .providerId(providerId)
                    .serviceCategory(dto.getServiceCategory())
                    .latitude(dto.getLatitude())
                    .longitude(dto.getLongitude())
                    .status(BookingStatus.REQUESTED)
                    .estimatedPrice(100.0)
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
        try{
            List<Booking> list = bookingDao.findByUserId(userId);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getProviderBooking(Long providerId) {
        try{
            List<Booking> list = bookingDao.findByProviderId(providerId);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> updateStatus(Long bookingId, String status) {
        try{
            Booking booking = bookingDao.findById(bookingId)
                    .orElseThrow(() -> new RuntimeException("Booking not found"));
            try{
                booking.setStatus(BookingStatus.valueOf(status.toUpperCase()));
            } catch (Exception e) {
                return new ResponseEntity<>("Invalid status", HttpStatus.BAD_REQUEST);
            }
            bookingDao.save(booking);
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> cancelBooking(Long bookingId) {
        try{
            Booking booking = bookingDao.findById(bookingId)
                    .orElseThrow(() -> new RuntimeException("Booking not found"));

            booking.setStatus(BookingStatus.CANCELLED);
            bookingDao.save(booking);

            return new ResponseEntity<>("Booking cancelled", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
