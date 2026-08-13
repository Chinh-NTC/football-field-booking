package com.demo.footballbooking.controller;

import com.demo.footballbooking.dto.PitchRequestDTO;
import com.demo.footballbooking.entity.Pitch;
import com.demo.footballbooking.service.PitchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owner/pitches")
public class PitchController {

    @Autowired
    private PitchService pitchService;

    // Tạm thời hardcode ownerId = 1 (Tự động lấy sau khi làm phần Login JWT)
    @PostMapping
    public ResponseEntity<Pitch> createPitch(@Valid @RequestBody PitchRequestDTO request) {
        Long ownerId = 1L; 
        Pitch pitch = pitchService.createPitch(ownerId, request);
        return ResponseEntity.ok(pitch);
    }

    @GetMapping
    public ResponseEntity<List<Pitch>> getPitches() {
        return ResponseEntity.ok(pitchService.getAllPitches());
    }
}