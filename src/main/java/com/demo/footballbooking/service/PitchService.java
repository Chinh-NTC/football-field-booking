package com.demo.footballbooking.service;

import com.demo.footballbooking.dto.PitchRequestDTO;
import com.demo.footballbooking.entity.Pitch;
import com.demo.footballbooking.entity.PitchCourt;
import com.demo.footballbooking.repository.PitchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PitchService {

    @Autowired
    private PitchRepository pitchRepository;

    public Pitch createPitch(Long ownerId, PitchRequestDTO request) {
        Pitch pitch = new Pitch();
        pitch.setOwnerId(ownerId);
        pitch.setName(request.getName());
        pitch.setAddress(request.getAddress());

        // Nếu chủ sân nhập luôn thông tin sân con lúc tạo cụm sân
        if (request.getCourtName() != null) {
            PitchCourt court = new PitchCourt();
            court.setCourtName(request.getCourtName());
            court.setCourtType(request.getCourtType());
            court.setBasePricePerHour(request.getBasePricePerHour());
            court.setPitch(pitch); // Gán sân con này thuộc cụm sân vừa tạo
            pitch.setCourts(List.of(court)); // Thêm vào danh sách sân con
        }

        return pitchRepository.save(pitch); // Cascade.ALL sẽ tự lưu cả PitchCourt xuống DB
    }

    public List<Pitch> getAllPitches() {
        return pitchRepository.findAll();
    }
}