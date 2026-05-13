package com.servicehub.user.restImpl;

import com.servicehub.user.dto.CreateUserRequest;
import com.servicehub.user.rest.UserInternalRest;
import com.servicehub.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserInternalRestImpl implements UserInternalRest {

    private final UserService userService;

    @Override
    public ResponseEntity<?> createUser(CreateUserRequest request) {
        userService.createUser(request);
        return ResponseEntity.ok("User Created");
    }
}
