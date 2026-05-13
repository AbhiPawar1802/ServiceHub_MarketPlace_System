package com.servicehub.user.service;

import com.servicehub.user.constents.ActivityEventType;

public interface ActivityService {

    void recordEvent(ActivityEventType eventType);

}
