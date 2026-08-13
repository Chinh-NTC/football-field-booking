package com.demo.footballbooking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data // Tự động sinh getter/setter
public class UserRequestDTO {
    @NotBlank(message = "Username không được để trống")
    private String username;

    @NotBlank(message = "Password không được để trống")
    private String password;

    @NotBlank(message = "Role không được để trống")
    private String role; // PLAYER hoặc OWNER

    private String email;
}