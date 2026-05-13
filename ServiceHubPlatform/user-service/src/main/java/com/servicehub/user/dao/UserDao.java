package com.servicehub.user.dao;

import com.servicehub.user.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findByAuthUserId(Long authUserId);

}
