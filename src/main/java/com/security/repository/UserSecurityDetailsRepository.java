package com.security.repository;

import com.security.dto.UserSecurityDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSecurityDetailsRepository extends JpaRepository<UserSecurityDto, Integer> {
    Optional<UserSecurityDto> findByEmail(String email);
}
