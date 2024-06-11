package com.free2go.hospital.user.controller;

import com.free2go.hospital.user.domain.User;
import com.free2go.hospital.user.dto.UserCreateReq;
import com.free2go.hospital.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody final UserCreateReq dto) {
        userService.createUser(dto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
