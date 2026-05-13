package com.servicehub.user.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SavedProviderResponse {

    private String providerId;
    private String providerName;
    private LocalDateTime savedAt;

}
