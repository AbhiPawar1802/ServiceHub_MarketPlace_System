package com.servicehub.auth.dao;

import com.servicehub.auth.POJO.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserDao extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByEmail(String email);

}
