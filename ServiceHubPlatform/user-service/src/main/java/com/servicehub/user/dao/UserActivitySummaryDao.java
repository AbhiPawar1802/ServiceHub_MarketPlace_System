package com.servicehub.user.dao;

import com.servicehub.user.POJO.UserActivitySummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserActivitySummaryDao extends JpaRepository<UserActivitySummary, Long> {

    Optional<UserActivitySummary> findByAuthUserId(Long authUserId);
}
