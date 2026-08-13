package com.demo.footballbooking.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Chuỗi secret key bí mật (Ra làm thật phải đưa vào biến môi trường .env)
    private final String SECRET_STRING = "my-super-secret-key-for-jwt-which-needs-to-be-long-enough-123456";
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes());

    // Thời gian sống của token: 1 ngày (tính bằng mili giây)
    private final long EXPIRATION_TIME = 86400000; 

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .subject(username)
                .claim("role", role) // Lưu role vào token để dùng cho phân quyền sau
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }
}