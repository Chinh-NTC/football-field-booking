package com.demo.footballbooking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pitch_courts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PitchCourt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pitch_id", nullable = false)
    private Pitch pitch;

    @Column(nullable = false)
    private String courtName; // Tên sân (VD: Sân A, Sân 1)

    @Column(nullable = false)
    private String courtType; // 5v5, 7v7

    @Column(nullable = false)
    private BigDecimal basePricePerHour; // Giá tiền/giờ. Dùng BigDecimal cho tiền bạc là chuẩn nhất

    private LocalDateTime deletedAt;
}