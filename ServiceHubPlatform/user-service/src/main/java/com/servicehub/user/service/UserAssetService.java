package com.servicehub.user.service;

import com.servicehub.user.dto.UploadAssetRequest;
import com.servicehub.user.dto.UserAssetResponse;

import java.util.List;

public interface UserAssetService {

    UserAssetResponse uploadAsset(UploadAssetRequest request);

    List<UserAssetResponse> getMyAssets();

}
