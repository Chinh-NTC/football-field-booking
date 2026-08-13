package com.demo.footballbooking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @GetMapping("/hello")
    public String hello() {
        return "Chào mừng đến với Nền tảng Sân Bóng Phong Trào!";
    }
}