package com.servicehub.auth.JWT;

import com.servicehub.auth.POJO.AuthUser;
import com.servicehub.auth.dao.AuthUserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthUserDao authUserDao;

    private Optional<AuthUser> authUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        log.info("Inside loadUserByUsername {}", username);

        authUser=authUserDao.findByEmail(username);
        if(authUser.isEmpty()){
            throw new UsernameNotFoundException("User not found.");
        }
        return new User(
                authUser.get().getEmail(),
                authUser.get().getPassword(),
                List.of(new SimpleGrantedAuthority(authUser.get().getRole()))
        );
    }
    public Optional<AuthUser> getAuthUser(){
        return authUser;
    }
}
