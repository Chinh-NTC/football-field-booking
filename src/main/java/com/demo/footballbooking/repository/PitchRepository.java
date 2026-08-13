package com.demo.footballbooking.repository;

import com.demo.footballbooking.entity.Pitch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PitchRepository extends JpaRepository<Pitch, Long> {
}