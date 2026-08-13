package com.demo.footballbooking.service;

import com.demo.footballbooking.entity.Booking;
import com.demo.footballbooking.entity.Payment;
import com.demo.footballbooking.repository.BookingRepository;
import com.demo.footballbooking.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // Hàm này được gọi ngay sau khi tạo Booking thành công
    public void createPayment(Booking booking) {
        Payment payment = new Payment();
        payment.setBookingId(booking.getId());
        payment.setAmount(booking.getDepositAmount());
        payment.setProvider("VIETQR");
        payment.setStatus("INITIATED");
        payment.setCreatedAt(LocalDateTime.now());
        paymentRepository.save(payment);
    }

    // Hàm giả lập Webhook ngân hàng gọi về báo đã nhận tiền
    public String confirmPayment(Long bookingId) {
        // 1. Tìm đơn đặt sân
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn đặt sân"));

        // 2. Tìm thông tin thanh toán
        Payment payment = paymentRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin thanh toán"));

        // 3. Kiểm tra xem webhook có bị gọi lại lần 2 không (Idempotency cơ bản)
        if (payment.getStatus().equals("SUCCESS")) {
            return "Giao dịch này đã được xác nhận trước đó, bỏ qua request trùng lặp.";
        }

        // 4. Cập nhật trạng thái
        payment.setStatus("SUCCESS");
        payment.setTransactionId("TXN" + System.currentTimeMillis()); // Sinh mã giao dịch giả
        paymentRepository.save(payment);

        // 5. Cập nhật trạng thái Booking
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);

        return "Thanh toán thành công! Đặt sân đã được xác nhận.";
    }
}