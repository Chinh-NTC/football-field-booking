package com.demo.footballbooking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Tắt chống tấn công CSRF (cho mục đích test API)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Tạm thời cho phép tất cả request đi qua mà không cần token
            );
        return http.build();
    }
}