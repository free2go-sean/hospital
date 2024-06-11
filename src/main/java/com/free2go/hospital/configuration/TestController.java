package com.free2go.hospital.configuration;

import com.free2go.hospital.user.domain.User;
import com.free2go.hospital.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

/*
* TODO: 테스트를 위한 Controller
*  삭제 처리 해야함.
* */
@RequiredArgsConstructor
@RequestMapping("/tests")
@RestController
public class TestController {
    private final TokenProvider tokenProvider;
    private final UserService userService;

    @PostMapping("/access-token/{id}")
    public ResponseEntity<String> createAccessToken(HttpServletResponse response, @PathVariable("id") Long id) {
        User user = userService.findById(id);

        String token = tokenProvider.generateToken(user, Duration.ofMinutes(30));
        response.setHeader("Authorization", "Bearer " + token);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/refresh-token/{id}")
    public ResponseEntity<String> createRefreshToken(HttpServletResponse response, @PathVariable("id") Long id) {
        User user = userService.findById(id);

        String token = tokenProvider.generateToken(user, Duration.ofDays(30));
        response.setHeader("Set-Cookie", "refreshToken=" + token + "; HttpOnly; Max-Age=" + (60*60*24*30));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
