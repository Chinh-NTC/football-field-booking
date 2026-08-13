package com.demo.footballbooking.service;

import com.demo.footballbooking.dto.UserRequestDTO;
import com.demo.footballbooking.dto.UserResponseDTO;
import com.demo.footballbooking.entity.User;
import com.demo.footballbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Hàm tạo User
    public UserResponseDTO createUser(UserRequestDTO request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // Tạm thời lưu thẳng, sau này mã hóa
        user.setRole(request.getRole());
        user.setEmail(request.getEmail());

        User savedUser = userRepository.save(user);

        UserResponseDTO response = new UserResponseDTO();
        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());
        return response;
    }

    // Hàm lấy danh sách User
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> {
            UserResponseDTO response = new UserResponseDTO();
            response.setId(user.getId());
            response.setUsername(user.getUsername());
            response.setEmail(user.getEmail());
            response.setRole(user.getRole());
            return response;
        }).collect(Collectors.toList());
    }
}