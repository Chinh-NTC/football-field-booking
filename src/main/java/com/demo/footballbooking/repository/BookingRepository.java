package com.demo.footballbooking.repository;

import com.demo.footballbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Query kiểm tra xem sân này có bị trùng giờ không
    // Logic trùng lịch: Giờ bắt đầu mới < Giờ kết thúc cũ VÀ Giờ kết thúc mới > Giờ bắt đầu cũ
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.courtId = :courtId " +
           "AND b.status IN ('PENDING', 'CONFIRMED') " +
           "AND b.startTime < :endTime AND b.endTime > :startTime")
    long countOverlappingBookings(@Param("courtId") Long courtId, 
                                  @Param("startTime") LocalDateTime startTime, 
                                  @Param("endTime") LocalDateTime endTime);
}