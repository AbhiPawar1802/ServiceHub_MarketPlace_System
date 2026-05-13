package com.servicehub.user.serviceImpl;

import com.servicehub.user.POJO.UserAsset;
import com.servicehub.user.dao.UserAssetDao;
import com.servicehub.user.dto.UploadAssetRequest;
import com.servicehub.user.dto.UserAssetResponse;
import com.servicehub.user.service.UserAssetService;
import com.servicehub.user.utils.JwtUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAssetServiceImpl implements UserAssetService {

    private final UserAssetDao dao;

    @Override
    public UserAssetResponse uploadAsset(UploadAssetRequest request) {

        UserAsset asset = new UserAsset();
        asset.setAuthUserId(JwtUserContext.getAuthUserId());
        asset.setFileName(request.getFileName());
        asset.setFileUrl(request.getFileUrl());
        asset.setFileType(request.getFileType());
        asset.setFileSizeKb(request.getFileSizeKb());
        asset.setCategory(request.getCategory());
        asset.setAssetType(request.getAssetType());
        asset.setServiceType(request.getServiceType());
        asset.setAssetId(UUID.randomUUID().toString());

        dao.save(asset);
        return map(asset);
    }

    @Override
    public List<UserAssetResponse> getMyAssets() {

        return dao.findByAuthUserId(JwtUserContext.getAuthUserId())
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private UserAssetResponse map(UserAsset asset){
        return UserAssetResponse.builder()
                .id(asset.getId())
                .fileName(asset.getFileName())
                .fileUrl(asset.getFileUrl())
                .fileType(asset.getFileType())
                .fileSizeKb(asset.getFileSizeKb())
                .category(asset.getCategory())
                .assetType(asset.getAssetType())
                .assetType(asset.getServiceType())
                .serviceType(asset.getServiceType())
                .uploadedAt(asset.getUploadedAt())
                .build();
    }
}
