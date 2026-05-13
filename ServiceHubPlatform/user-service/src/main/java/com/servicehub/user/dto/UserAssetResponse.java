package com.servicehub.user.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserAssetResponse {

    private Long id;
    private String fileName;
    private String fileUrl;
    private String fileType;
    private Long fileSizeKb;
    private String category;
    private String assetType;
    private String serviceType;
    private LocalDateTime uploadedAt;

}
