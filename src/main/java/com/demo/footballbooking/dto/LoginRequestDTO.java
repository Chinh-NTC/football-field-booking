package com.demo.footballbooking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotBlank(message = "Username không được trống")
    private String username;

    @NotBlank(message = "Password không được trống")
    private String password;
}