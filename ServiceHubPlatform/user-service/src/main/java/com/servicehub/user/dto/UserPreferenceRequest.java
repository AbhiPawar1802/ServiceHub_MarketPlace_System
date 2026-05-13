package com.servicehub.user.dto;

import lombok.Data;

@Data
public class UserPreferenceRequest {

    private String preferredLanguage;
    private boolean notificationEnabled;
    private Double serviceRadiusKm;
    private boolean autoAssignProvider;
    private String preferredServiceTime;
    private String preferredPaymentMode;
    private boolean allowCall;
    private boolean allowWhatsapp;

}
