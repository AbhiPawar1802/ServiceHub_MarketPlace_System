package com.servicehub.user.rest;

import com.servicehub.user.dto.UserActivitySummaryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface UserActivityRest {

    @GetMapping("/activity-summary")
    ResponseEntity<UserActivitySummaryResponse> getActivitySummary();
}
