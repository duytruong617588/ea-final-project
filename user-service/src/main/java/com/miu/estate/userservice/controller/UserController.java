package com.miu.estate.userservice.controller;

import com.miu.estate.userservice.dto.response.UserDetailResponse;
import com.miu.estate.userservice.model.User;
import com.miu.estate.userservice.service.UserService;
import com.miu.estate.userservice.utils.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/verification")
    public ResponseEntity<?> getUserById(HttpServletRequest request) {
        User userLogin = RequestUtil.getUserLogin(request).orElseThrow(() -> new RuntimeException("User not found!"));
        return ResponseEntity.ok().body(UserDetailResponse.fromUserEntity(userLogin));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().body(userService.getListProperties());
    }
}
