package com.servicehub.user.rest;

import com.servicehub.user.dto.UploadAssetRequest;
import com.servicehub.user.dto.UserAssetResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface UserAssetRest {

    @PostMapping("/assets")
    ResponseEntity<UserAssetResponse> uploadAsset(@RequestBody UploadAssetRequest request);

    @GetMapping("/assets")
    ResponseEntity<List<UserAssetResponse>> getMyAssets();

}
