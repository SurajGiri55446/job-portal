package com.jobpotal.job.services;

import com.jobpotal.job.DTO.UserDto;
import com.jobpotal.job.Repositories.UserRepository;
import com.jobpotal.job.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User userRegister(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        User user = new User();
        user.setFullname(userDto.getFullname());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // encode password
        user.setRole(userDto.getRole());

        return userRepository.save(user);
    }

    public User userEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
