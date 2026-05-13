package com.servicehub.user.rest;

import com.servicehub.user.dto.AddressResponse;
import com.servicehub.user.dto.CreateAddressRequest;
import com.servicehub.user.dto.UpdateUserRequest;
import com.servicehub.user.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserRest {

    @GetMapping("/me")
    ResponseEntity<UserResponse> getProfile();

    @PutMapping("/me")
    ResponseEntity<UserResponse> updateProfile(
            @RequestBody UpdateUserRequest request);

    @PostMapping("/address")
    ResponseEntity<AddressResponse> addAddress(@RequestBody CreateAddressRequest request);

    @GetMapping("/address")
    ResponseEntity<List<AddressResponse>> getAddress();

    @PutMapping("/address/default/{id}")
    ResponseEntity<?> setDefault(@PathVariable Long id);

    @DeleteMapping("/address/{id}")
    ResponseEntity<?> deleteAddress(@PathVariable Long id);

}
