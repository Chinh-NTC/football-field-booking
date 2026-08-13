package com.demo.footballbooking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password; // Tạm thời lưu text thường, sau này sẽ mã hóa bằng BCrypt

    @Column(nullable = false)
    private String role; // Tạm thời lưu chuỗi: "PLAYER" hoặc "OWNER"

    @Column(unique = true)
    private String email;
}