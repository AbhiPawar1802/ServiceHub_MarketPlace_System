package com.servicehub.user.dao;

import com.servicehub.user.POJO.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPreferenceDao extends JpaRepository<UserPreference, Long> {

    Optional<UserPreference> findByAuthUserId(Long authUserId);

}
