package com.demo.footballbooking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PitchRequestDTO {
    @NotBlank(message = "Tên cụm sân không được trống")
    private String name;

    @NotBlank(message = "Địa chỉ không được trống")
    private String address;

    // Owner có thể luôn tạo luôn sân con đầu tiên
    private String courtName;
    private String courtType;
    private BigDecimal basePricePerHour;
}