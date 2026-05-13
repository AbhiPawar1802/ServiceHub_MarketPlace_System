package com.servicehub.user.restImpl;

import com.servicehub.user.dto.UserActivitySummaryResponse;
import com.servicehub.user.rest.UserActivityRest;
import com.servicehub.user.service.UserActivitySummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserActivityRestImpl implements UserActivityRest {

    private final UserActivitySummaryService userActivitySummaryService;

    @Override
    public ResponseEntity<UserActivitySummaryResponse> getActivitySummary() {
        return ResponseEntity.ok(userActivitySummaryService.getActivitySummary());
    }
}
