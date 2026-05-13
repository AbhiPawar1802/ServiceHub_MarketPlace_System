package com.servicehub.user.serviceImpl;

import com.servicehub.user.POJO.UserActivitySummary;
import com.servicehub.user.dao.UserActivitySummaryDao;
import com.servicehub.user.dto.UserActivitySummaryResponse;
import com.servicehub.user.service.UserActivitySummaryService;
import com.servicehub.user.utils.JwtUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserActivitySummaryServiceImpl implements UserActivitySummaryService {

    private final UserActivitySummaryDao dao;

    @Override
    public UserActivitySummaryResponse getActivitySummary() {
        Long authUserId = JwtUserContext.getAuthUserId();

        UserActivitySummary summary = dao.findByAuthUserId(authUserId)
                .orElseGet(() -> createEmptySummary(authUserId));

        return map(summary);
    }

    private UserActivitySummary createEmptySummary(Long authUserId) {
        UserActivitySummary summary = new UserActivitySummary();
        summary.setAuthUserId(authUserId);
        return dao.save(summary);
    }

    @Override
    public void updateLastLogin() {

        Long authUserId = JwtUserContext.getAuthUserId();

        UserActivitySummary summary = dao.findByAuthUserId(authUserId)
                .orElseGet(() -> createEmptySummary(authUserId));

        summary.setLastLogin(LocalDateTime.now());
        dao.save(summary);

    }

    private UserActivitySummaryResponse map(UserActivitySummary s){
        return UserActivitySummaryResponse.builder()
                .totalBooking(s.getTotalBooking())
                .completedBooking(s.getCompletedBooking())
                .cancelledBookings(s.getCancelledBookings())
                .totalSpent(s.getTotalSpent())
                .savedProviderCount(s.getSavedProvidersCount())
                .addressCount(s.getAddressCount())
                .lastBookingDate(s.getLastBookingDate())
                .mostUsedServiceCategory(s.getMostUsedServiceCategory())
                .lastLogin(s.getLastLogin())
                .build();
    }
}
