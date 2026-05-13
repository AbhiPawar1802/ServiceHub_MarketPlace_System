package com.servicehub.user.POJO;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_assets")
@Data
public class UserAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long authUserId;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Column(name = "file_size_kb")
    private Long fileSizeKb;

    @Column(name = "Category")
    private String category;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @Column(name = "asset_id", nullable = false)
    private String assetId;

    @Column(name = "asset_type", nullable = false)
    private String assetType;

    @Column(name = "service_type", nullable = false)
    private String serviceType;

    @PrePersist
    public void onCreate(){
        this.uploadedAt = LocalDateTime.now();
    }

}
