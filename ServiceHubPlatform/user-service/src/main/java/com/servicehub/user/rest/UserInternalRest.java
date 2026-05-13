package com.servicehub.user.rest;

import com.servicehub.user.dto.CreateUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserInternalRest {

    @PostMapping("/internal/create")
    ResponseEntity<?> createUser(@RequestBody CreateUserRequest request);

}
