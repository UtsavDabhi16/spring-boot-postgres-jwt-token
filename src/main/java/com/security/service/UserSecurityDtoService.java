package com.security.service;

import com.security.dto.UserSecurityDto;
import com.security.repository.UserSecurityDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSecurityDtoService {

    private final UserSecurityDetailsRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserSecurityDtoService(UserSecurityDetailsRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserSecurityDto saveOrUpdate(UserSecurityDto user) {
        // Ensure the password is encoded before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<UserSecurityDto> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserSecurityDto> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
