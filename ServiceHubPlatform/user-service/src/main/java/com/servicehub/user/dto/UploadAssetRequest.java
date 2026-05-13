package com.servicehub.user.dto;

import lombok.Data;

@Data
public class UploadAssetRequest {

    private String fileName;
    private String fileUrl;
    private String fileType;
    private Long fileSizeKb;
    private String category;
    private String assetType;
    private String serviceType;

}
