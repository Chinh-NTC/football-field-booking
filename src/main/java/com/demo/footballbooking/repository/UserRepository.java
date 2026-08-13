package com.demo.footballbooking.repository;

import com.demo.footballbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Tự sinh query: SELECT * FROM users WHERE username = ?
    Optional<User> findByUsername(String username);
}