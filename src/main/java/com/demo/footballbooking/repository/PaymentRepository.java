package com.demo.footballbooking.repository;

import com.demo.footballbooking.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Tìm payment theo bookingId
    Optional<Payment> findByBookingId(Long bookingId);
}