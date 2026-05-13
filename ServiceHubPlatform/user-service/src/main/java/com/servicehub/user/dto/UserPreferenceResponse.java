package com.servicehub.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPreferenceResponse {

    private String preferredLanguage;
    private boolean notificationEnabled;
    private Double serviceRadiusKm;
    private boolean autoAssignProvider;
    private String preferredServiceTime;
    private String preferredPaymentMode;
    private boolean allowCall;
    private boolean allowWhatsapp;

}
