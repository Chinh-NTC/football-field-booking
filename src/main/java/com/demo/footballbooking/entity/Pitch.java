package com.demo.footballbooking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pitches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pitch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Khóa ngoại nối với User (với vai trò là OWNER)
    @Column(nullable = false)
    private Long ownerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    // Cơ chế Soft Delete (Xóa mềm): Không xóa hẳn bản ghi, chỉ đánh dấu thời gian xóa
    private LocalDateTime deletedAt;

    // Quan hệ 1-Nhiều: 1 Pitch có nhiều PitchCourt
    // cascade: Khi lưu Pitch, tự động lưu luôn các Court bên trong
    @OneToMany(mappedBy = "pitch", cascade = CascadeType.ALL)
    private List<PitchCourt> courts;
}