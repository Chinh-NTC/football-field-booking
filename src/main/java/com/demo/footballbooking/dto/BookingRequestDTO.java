package com.demo.footballbooking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingRequestDTO {
    @NotNull(message = "User ID không được trống")
    private Long userId;

    @NotNull(message = "Court ID không được trống")
    private Long courtId;

    @NotNull(message = "Thời gian bắt đầu không được trống")
    private LocalDateTime startTime;

    @NotNull(message = "Thời gian kết thúc không được trống")
    private LocalDateTime endTime;
}