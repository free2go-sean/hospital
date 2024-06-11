package com.free2go.hospital.user.service;

import com.free2go.hospital.user.domain.User;
import com.free2go.hospital.user.dto.UserCreateReq;
import com.free2go.hospital.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User createUser(UserCreateReq dto) {
        User user = userRepository.save(dto.toEntity());

        return user;
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }
}
