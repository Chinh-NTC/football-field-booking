package com.demo.footballbooking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long bookingId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String provider; // VIETQR, MOMO, CASH

    @Column(nullable = false)
    private String status; // INITIATED (Chờ thanh toán), SUCCESS (Thành công)

    private String transactionId; // Mã giao dịch ngân hàng trả về

    @Column(nullable = false)
    private LocalDateTime createdAt;
}