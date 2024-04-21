package com.miu.estate.userservice.controller;

import com.miu.estate.userservice.dto.response.UserDetailResponse;
import com.miu.estate.userservice.model.ResetPasswordRequest;
import com.miu.estate.userservice.model.User;
import com.miu.estate.userservice.service.UserService;
import com.miu.estate.userservice.utils.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getUserByToken(HttpServletRequest request) {
        User userLogin = RequestUtil.getUserLogin(request).orElseThrow(() -> new RuntimeException("User not found!"));
        return ResponseEntity.ok().body(UserDetailResponse.fromUserEntity(userLogin));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId, HttpServletRequest request) {
        return ResponseEntity.ok().body(userService.getUserById(userId));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().body(userService.getListProperties());
    }

    @PutMapping("/{id}/reset/password")
    public ResponseEntity<?> resetPassword(@PathVariable Long id, @RequestBody ResetPasswordRequest req) {
        return ResponseEntity.ok(userService.resetUserPassword(id, req.getNewPassword()));
    }

    @PutMapping("/{id}/active/{status}")
    public ResponseEntity<?> changeUserStatus(@PathVariable Long id, @PathVariable String status) {
        return ResponseEntity.ok(userService.changeUserStatus(id, status));
    }

    @PutMapping("/{id}/approval/{isApprove}")
    public ResponseEntity<?> approveProfile(@PathVariable Long id, @PathVariable Boolean isApprove) {
        return ResponseEntity.ok(userService.setProfileApproval(id,isApprove));
    }
}
