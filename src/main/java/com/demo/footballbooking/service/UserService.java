package com.demo.footballbooking.service;

import com.demo.footballbooking.dto.LoginRequestDTO;
import com.demo.footballbooking.dto.UserRequestDTO;
import com.demo.footballbooking.dto.UserResponseDTO;
import com.demo.footballbooking.entity.User;
import com.demo.footballbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import com.demo.footballbooking.security.JwtUtil;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

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

    public String login(LoginRequestDTO request) {
        // 1. Tìm user theo username
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Sai tên đăng nhập hoặc mật khẩu"));

        // 2. Kiểm tra mật khẩu (MVP tạm so sánh chuỗi trực tiếp. Ra làm thật phải dùng BCrypt)
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Sai tên đăng nhập hoặc mật khẩu");
        }

        // 3. Nếu đúng, tạo token và trả về
        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}