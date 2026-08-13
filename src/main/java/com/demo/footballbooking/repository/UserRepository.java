package com.demo.footballbooking.repository;

import com.demo.footballbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Kế thừa JpaRepository là có sẵn hàm save(), findAll() rồi, không cần viết gì thêm ở MVP này
}