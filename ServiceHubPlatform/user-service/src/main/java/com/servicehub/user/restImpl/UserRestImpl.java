package com.servicehub.user.restImpl;

import com.servicehub.user.dto.AddressResponse;
import com.servicehub.user.dto.CreateAddressRequest;
import com.servicehub.user.dto.UpdateUserRequest;
import com.servicehub.user.dto.UserResponse;
import com.servicehub.user.rest.UserRest;
import com.servicehub.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestImpl implements UserRest {

    private final UserService userService;

    @Override
    public ResponseEntity<UserResponse> getProfile() {
        return ResponseEntity.ok(userService.getMyProfile());
    }

    @Override
    public ResponseEntity<UserResponse> updateProfile(@Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateMyProfile(request));
    }

    @Override
    public ResponseEntity<AddressResponse> addAddress(CreateAddressRequest request) {
        return ResponseEntity.ok(userService.addAddress(request));
    }

    @Override
    public ResponseEntity<List<AddressResponse>> getAddress() {
        return ResponseEntity.ok(userService.getMyAddress());
    }

    @Override
    public ResponseEntity<?> setDefault(Long id) {
        userService.setDefaultAddress(id);
        return ResponseEntity.ok("Default address updated");
    }

    @Override
    public ResponseEntity<?> deleteAddress(Long id) {
        userService.deleteAddress(id);
        return ResponseEntity.ok("Address deleted successfully");
    }
}
