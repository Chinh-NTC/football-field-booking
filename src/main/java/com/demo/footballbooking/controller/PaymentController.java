package com.demo.footballbooking.controller;

import com.demo.footballbooking.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // API giả lập Webhook (Như ngân hàng gọi về hệ thống)
    @PostMapping("/webhook/{bookingId}")
    public ResponseEntity<String> mockPaymentWebhook(@PathVariable Long bookingId) {
        String result = paymentService.confirmPayment(bookingId);
        return ResponseEntity.ok(result);
    }
}