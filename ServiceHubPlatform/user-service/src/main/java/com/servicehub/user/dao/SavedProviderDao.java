package com.servicehub.user.dao;

import com.servicehub.user.POJO.SavedProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SavedProviderDao extends JpaRepository<SavedProvider, Long> {

    List<SavedProvider> findByAuthUserId(Long authUserId);

    Optional<SavedProvider> findByAuthUserIdAndProviderId(Long authUserId, String providerId);

    void deleteByAuthUserIdAndProviderId(Long authUserId, String providerId);

}
