package com.miu.estate.admin.controller;

import com.miu.estate.admin.model.ResetPasswordRequest;
import com.miu.estate.admin.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class AdminUserController {
	private final AdminUserService adminUserService;

	@GetMapping("/list")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok().body(adminUserService.getAll());
	}

	@PutMapping("/{id}/reset/password")
	public ResponseEntity<?> resetPassword(@PathVariable Long id, @RequestBody ResetPasswordRequest req) {
		return ResponseEntity.ok(adminUserService.resetUserPassword(id, req));
	}

    @PutMapping("/{id}/active/{status}")
    public ResponseEntity<?> changeUserStatus(@PathVariable Long id, @PathVariable String status) {
        return ResponseEntity.ok(adminUserService.changeUserStatus(id, status));
    }

    @PutMapping("/{id}/approval/{isApprove}")
    public ResponseEntity<?> approveProfile(@PathVariable Long id, @PathVariable Boolean isApprove) {
        return ResponseEntity.ok(adminUserService.setProfileApproval(id,isApprove));
    }

}
