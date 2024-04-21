package com.miu.estate.admin.controller;

import com.miu.estate.admin.client.UserClient;
import com.miu.estate.admin.model.ResetPasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserClient userClient;

    @GetMapping("/list")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(userClient.getAll());
    }

    @PutMapping("/user/{id}/reset/password")
    public ResponseEntity<?> resetPassword(@PathVariable Long id, @RequestBody ResetPasswordRequest req) {
        return ResponseEntity.ok(userClient.resetUserPassword(id, req.getNewPassword()));
    }

    @PutMapping("/user/{id}/active/{status}")
    public ResponseEntity<?> changeUserStatus(@PathVariable Long id, @PathVariable String status) {
        return ResponseEntity.ok(userClient.changeUserStatus(id, status));
    }

    @PutMapping("/profile/{id}/approval/{isApprove}")
    public ResponseEntity<?> approveProfile(@PathVariable Long id, @PathVariable Boolean isApprove) {
        return ResponseEntity.ok(userClient.setProfileApproval(id,isApprove));
    }

}
