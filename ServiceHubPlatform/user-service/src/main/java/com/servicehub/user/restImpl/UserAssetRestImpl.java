package com.servicehub.user.restImpl;

import com.servicehub.user.dto.UploadAssetRequest;
import com.servicehub.user.dto.UserAssetResponse;
import com.servicehub.user.rest.UserAssetRest;
import com.servicehub.user.service.UserAssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAssetRestImpl implements UserAssetRest {

    private final UserAssetService service;

    @Override
    public ResponseEntity<UserAssetResponse> uploadAsset(UploadAssetRequest request) {
        return ResponseEntity.ok(service.uploadAsset(request));
    }

    @Override
    public ResponseEntity<List<UserAssetResponse>> getMyAssets() {
        return ResponseEntity.ok(service.getMyAssets());
    }
}
