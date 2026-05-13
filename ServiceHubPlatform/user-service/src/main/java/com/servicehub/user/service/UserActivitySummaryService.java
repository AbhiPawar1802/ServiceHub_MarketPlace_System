package com.servicehub.user.service;

import com.servicehub.user.dto.UserActivitySummaryResponse;

public interface UserActivitySummaryService {

    UserActivitySummaryResponse getActivitySummary();

    void updateLastLogin();

}
