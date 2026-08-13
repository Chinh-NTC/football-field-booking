package com.demo.footballbooking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String refCode; // Mã đơn (VD: BK1234)

    @Column(nullable = false)
    private Long userId; // Người đặt

    @Column(nullable = false)
    private Long courtId; // Sân con được đặt

    @Column(nullable = false)
    private LocalDateTime startTime; // Giờ bắt đầu

    @Column(nullable = false)
    private LocalDateTime endTime; // Giờ kết thúc

    @Column(nullable = false)
    private BigDecimal depositAmount; // Tiền cọc

    @Column(nullable = false)
    private BigDecimal totalPrice; // Tổng tiền

    // PENDING (Chờ thanh toán), CONFIRMED (Đã thanh toán), CANCELLED (Đã hủy)
    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}