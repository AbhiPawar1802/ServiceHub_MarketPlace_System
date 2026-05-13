package com.servicehub.user.serviceImpl;

import com.servicehub.user.POJO.UserActivitySummary;
import com.servicehub.user.constents.ActivityEventType;
import com.servicehub.user.dao.UserActivitySummaryDao;
import com.servicehub.user.service.ActivityService;
import com.servicehub.user.utils.JwtUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final UserActivitySummaryDao summaryDao;

    @Override
    public void recordEvent(ActivityEventType eventType) {

        Long authUserId = JwtUserContext.getAuthUserId();
        UserActivitySummary summary = summaryDao
                .findByAuthUserId(authUserId)
                .orElseGet(() -> createNewSummary(authUserId));

        switch (eventType) {
            case LOGIN -> summary.setLastLogin(LocalDateTime.now());

            case ADDRESS_ADDED -> summary.setAddressCount(
                    safe(summary.getAddressCount()) + 1);

            case PROVIDER_SAVED -> summary.setSavedProvidersCount(
                    safe(summary.getSavedProvidersCount()) + 1);

            case ADDRESS_REMOVED -> summary.setAddressCount(
                    Math.max(0, safe(summary.getAddressCount()) - 1));

            case PROVIDER_UNSAVED -> summary.setSavedProvidersCount(
                    Math.max(0, safe(summary.getSavedProvidersCount()) - 1));

            case BOOKING_CREATED -> summary.setTotalBooking(
                    safe(summary.getTotalBooking()) + 1);

            case BOOKING_COMPLETED -> summary.setCompletedBooking(
                    safe(summary.getCompletedBooking()) + 1);

            case BOOKING_CANCELLED -> summary.setCancelledBookings(
                    safe(summary.getCancelledBookings()) + 1);
        }
        summaryDao.save(summary);
    }

    private Long safe(Long value) {
        return value == null ? 0L : value;
    }

    private UserActivitySummary createNewSummary(Long authUserId) {
        UserActivitySummary summary = new UserActivitySummary();
        summary.setAuthUserId(authUserId);
        summary.setTotalBooking(0L);
        summary.setCompletedBooking(0L);
        summary.setCancelledBookings(0L);
        summary.setSavedProvidersCount(0L);
        summary.setAddressCount(0L);
        summary.setTotalSpent(0.0);
        return summary;
    }
}
