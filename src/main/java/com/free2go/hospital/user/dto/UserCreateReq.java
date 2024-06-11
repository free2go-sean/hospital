package com.free2go.hospital.user.dto;

import com.free2go.hospital.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserCreateReq {
    private String email;
    private String password;

    @Builder
    public UserCreateReq(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User toEntity() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return User.builder()
                .email(this.email)
                .password(encoder.encode(this.password))
                .build();
    }
}
