package com.servicehub.user.POJO;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_preference")
@Data
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "auth_user_id", nullable = false, unique = true)
    private Long authUserId;

    @Column(name = "preferredLanguage")
    private String preferredLanguage;

    @Column(name = "notificationEnabled")
    private boolean notificationEnabled;

    @Column(name = "serviceRadiusKm")
    private Double serviceRadiusKm;

    @Column(name = "autoAssignProvider")
    private boolean autoAssignProvider;

    @Column(name = "preferredServiceTime", nullable = false)
    private String preferredServiceTime;

    @Column(name = "preferredPaymentMode", nullable = false)
    private String preferredPaymentMode;

    @Column(name = "allowCall", nullable = false)
    private boolean allowCall;

    @Column(name = "allowWhatsapp", nullable = false)
    private boolean allowWhatsapp;

}
