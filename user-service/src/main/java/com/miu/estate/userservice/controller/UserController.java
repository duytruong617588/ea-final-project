package com.miu.estate.userservice.controller;

import com.miu.estate.userservice.dto.request.CreateUserRequest;
import com.miu.estate.userservice.dto.request.UpdateUserRequest;
import com.miu.estate.userservice.dto.response.UserDetailResponse;
import com.miu.estate.userservice.model.ResetPasswordRequest;
import com.miu.estate.userservice.model.User;
import com.miu.estate.userservice.model.UserRole;
import com.miu.estate.userservice.model.UserStatus;
import com.miu.estate.userservice.service.UserService;
import com.miu.estate.userservice.utils.RequestUtil;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(u -> ResponseEntity.ok(UserDetailResponse.fromUserEntity(u)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createOne(@Valid @RequestBody CreateUserRequest userRequest) {
        User user = userRequest.getUserEntity();
        return userService.createUser(user)
                .map(u -> ResponseEntity.ok(UserDetailResponse.fromUserEntity(u)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOne(@PathVariable Long id, @RequestBody UpdateUserRequest userRequest) {
        User user = userRequest.getUserEntity();
        return userService.updateUser(id, user)
                .map(u -> ResponseEntity.ok(UserDetailResponse.fromUserEntity(u)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchUser(@RequestParam @Nullable String keyword, @RequestParam @Nullable UserRole userRole, @RequestParam @Nullable UserStatus userStatus) {
        return userService.searchUsers(keyword, userRole, userStatus)
                .map(users -> ResponseEntity.ok(
                        users.stream().map(UserDetailResponse::fromUserEntity).toList()
                ))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getUserDetailById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(u -> ResponseEntity.ok(UserDetailResponse.fromUserEntity(u)))
                .orElse(ResponseEntity.notFound().build());
    }
}
