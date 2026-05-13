package com.servicehub.user.serviceImpl;

import com.servicehub.user.POJO.Address;
import com.servicehub.user.POJO.User;
import com.servicehub.user.constents.ActivityEventType;
import com.servicehub.user.constents.UserConstents;
import com.servicehub.user.dao.AddressDao;
import com.servicehub.user.dao.UserDao;
import com.servicehub.user.dto.*;
import com.servicehub.user.service.ActivityService;
import com.servicehub.user.service.UserService;
import com.servicehub.user.utils.JwtUserContext;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Setter
public class UserServiceImpl implements UserService {

    private final AddressDao addressDao;

    private final UserDao userDao;

    private final ActivityService activityService;

    @Override
    public UserResponse getMyProfile() {

        Long authuserId = JwtUserContext.getAuthUserId();
        User user = userDao.findByAuthUserId(authuserId)
                .orElseGet(() -> createUserFromToken(authuserId));
        return map(user);
    }

    private User createUserFromToken(Long authuserId) {

        User user = User.builder()
                .authUserId(authuserId)
                .email("not-set")
                .name("New User")
                .phone("")
                .status("ACTIVE")
                .gender("NOT_SPECIFIED")
                .profilePhotoUrl("DEFAULT")
                .dateOfBirth(LocalDate.of(2000,1,1))
                .build();

        return userDao.save(user);
    }

    @Override
    public UserResponse updateMyProfile(UpdateUserRequest request) {
        User user = userDao.findByAuthUserId(
                JwtUserContext.getAuthUserId()
        ).orElseThrow(() ->
                new RuntimeException(UserConstents.USER_NOT_FOUND));

        user.setName(request.getName());
        user.setPhone(request.getPhone());
        userDao.save(user);

        return map(user);
    }

    @Override
    public AddressResponse addAddress(CreateAddressRequest request) {
        Long authUserId = JwtUserContext.getAuthUserId();

        if (Boolean.TRUE.equals(request.getIsDefault())) {
            addressDao.findByAuthUserIdAndIsDefaultTrue(authUserId)
                    .ifPresent(existing -> {
                        existing.setDefault(false);
                        addressDao.save(existing);
                    });
        }

        Address address = Address.builder()
                .authUserId(authUserId)
                .label(request.getLabel())
                .line1(request.getLine1())
                .line2(request.getLine2())
                .city(request.getCity())
                .state(request.getState())
                .pincode(request.getPincode())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .isDefault(Boolean.TRUE.equals(request.getIsDefault()))
                .build();

        addressDao.save(address);

        activityService.recordEvent(ActivityEventType.ADDRESS_ADDED);

        return mapAddress(address);
    }

    @Override
    public List<AddressResponse> getMyAddress() {
        Long authUserId = JwtUserContext.getAuthUserId();
        return addressDao.findByAuthUserId(authUserId)
                .stream()
                .map(this:: mapAddress)
                .toList();
    }

    @Override
    public void createUser(CreateUserRequest request) {
        if(userDao.findByAuthUserId(request.getAuthUserId()).isPresent()){
            return;
        }

        User user = User.builder()
                .authUserId(request.getAuthUserId())
                .email(request.getEmail())
                .name(request.getName())
                .phone(request.getPhone())
                .status("ACTIVE")
                .gender("NOT_SPECIFIED")
                .profilePhotoUrl("DEFAULT")
                .dateOfBirth(LocalDate.of(2000,1,1))
                .build();
        userDao.save(user);
    }

    @Override
    public void setDefaultAddress(Long addressId) {
        Long authUserId = JwtUserContext.getAuthUserId();

        Address address = addressDao.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        if(!address.getAuthUserId().equals(authUserId)){
            throw new RuntimeException("Unauthorized access");
        }

        addressDao.findByAuthUserIdAndIsDefaultTrue(authUserId)
                .ifPresent(existing -> {
                    existing.setDefault(false);
                    addressDao.save(existing);
                });

        address.setDefault(true);
        addressDao.save(address);
    }

    @Override
    public void deleteAddress(Long addressId) {
        Long authUserId = JwtUserContext.getAuthUserId();

        Address address = addressDao.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        if(!address.getAuthUserId().equals(authUserId)){
            throw new RuntimeException("Unauthorized access");
        }

        boolean wasDefault = address.isDefault();

        addressDao.delete(address);

        if(wasDefault){
            addressDao.findByAuthUserId(authUserId)
                    .stream()
                    .findFirst()
                    .ifPresent(a -> {
                        a.setDefault(true);
                        addressDao.save(a);
                    });
        }

        activityService.recordEvent(ActivityEventType.ADDRESS_REMOVED);
    }

    private UserResponse map(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .status(user.getStatus())
                .build();
    }

    private AddressResponse mapAddress(Address address){
        return AddressResponse.builder()
                .id(address.getId())
                .label(address.getLabel())
                .line1(address.getLine1())
                .line2(address.getLine2())
                .city(address.getCity())
                .state(address.getState())
                .pincode(address.getPincode())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .isDefault(address.isDefault())
                .build();
    }
}
