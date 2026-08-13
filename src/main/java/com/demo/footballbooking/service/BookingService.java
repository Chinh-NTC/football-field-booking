package com.demo.footballbooking.service;

import com.demo.footballbooking.dto.BookingRequestDTO;
import com.demo.footballbooking.entity.Booking;
import com.demo.footballbooking.entity.PitchCourt;
import com.demo.footballbooking.repository.BookingRepository;
import com.demo.footballbooking.repository.PitchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private PitchRepository pitchRepository; // Dùng để tìm court và tính tiền

    public Booking createBooking(BookingRequestDTO request) {
        // 1. Kiểm tra xem sân có tồn tại không (Tạm tìm qua Pitch)
        // Lưu ý: Em chưa tạo CourtRepository, tạm dùng logic này. Sau này tối ưu sau.
        PitchCourt court = pitchRepository.findAll().stream()
                .flatMap(p -> p.getCourts().stream())
                .filter(c -> c.getId().equals(request.getCourtId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sân con với ID: " + request.getCourtId()));

        // 2. LOGIC CHỐNG TRÙNG LỊCH
        long overlappingCount = bookingRepository.countOverlappingBookings(
                request.getCourtId(), request.getStartTime(), request.getEndTime());
        
        if (overlappingCount > 0) {
            throw new RuntimeException("Khung giờ này đã có người đặt! Vui lòng chọn giờ khác.");
        }

        // 3. Tính toán tiền (Tạm tính: số giờ * giá sân)
        long hours = ChronoUnit.HOURS.between(request.getStartTime(), request.getEndTime());
        BigDecimal totalPrice = court.getBasePricePerHour().multiply(BigDecimal.valueOf(hours));
        BigDecimal deposit = totalPrice.multiply(new BigDecimal("0.5")); // Cọc 50%

        // 4. Tạo Booking
        Booking booking = new Booking();
        booking.setRefCode("BK" + System.currentTimeMillis() / 1000); // Sinh mã đơn tự động
        booking.setUserId(request.getUserId());
        booking.setCourtId(request.getCourtId());
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setTotalPrice(totalPrice);
        booking.setDepositAmount(deposit);
        booking.setStatus("PENDING"); // Trạng thái chờ thanh toán
        booking.setCreatedAt(LocalDateTime.now());

        return bookingRepository.save(booking);
    }
}