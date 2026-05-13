package com.servicehub.user.dao;

import com.servicehub.user.POJO.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressDao extends JpaRepository<Address, Long> {

    List<Address> findByAuthUserId(Long authUserId);

    Optional<Address> findByAuthUserIdAndIsDefaultTrue(Long authUserId);
}
