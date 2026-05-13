package com.servicehub.user.dao;

import com.servicehub.user.POJO.UserAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAssetDao extends JpaRepository<UserAsset, Long> {

    List<UserAsset> findByAuthUserId(Long authUserId);
}
