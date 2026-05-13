package com.servicehub.user.service;

import com.servicehub.user.dto.*;

import java.util.List;

public interface UserService {

    UserResponse getMyProfile();

    UserResponse updateMyProfile(UpdateUserRequest request);

    AddressResponse addAddress(CreateAddressRequest request);

    List<AddressResponse> getMyAddress();

    void createUser(CreateUserRequest request);

    void setDefaultAddress(Long addressId);

    void deleteAddress(Long addressId);
}
