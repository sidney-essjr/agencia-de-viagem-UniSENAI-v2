package com.travel_agency.api.service;

import com.travel_agency.api.entity.UserEntity;
import com.travel_agency.api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public UserEntity registerUser(String username, String password) {
        if (userExists(username)) {
            throw new IllegalArgumentException("Usuário já existe");
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Set.of("ROLE_USER")); // Adiciona os papéis (roles) necessários

        return userRepository.save(user);
    }
}
