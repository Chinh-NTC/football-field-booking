package com.demo.footballbooking.controller;

import com.demo.footballbooking.dto.BookingRequestDTO;
import com.demo.footballbooking.entity.Booking;
import com.demo.footballbooking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody BookingRequestDTO request) {
        Booking booking = bookingService.createBooking(request);
        return ResponseEntity.ok(booking);
    }
}