package com.miu.estate.userservice.controller;

import com.miu.estate.userservice.dto.request.LoginRequest;
import com.miu.estate.userservice.dto.request.RegisterUserRequest;
import com.miu.estate.userservice.dto.response.RegisterUserResponse;
import com.miu.estate.userservice.dto.response.UserDetailResponse;
import com.miu.estate.userservice.model.User;
import com.miu.estate.userservice.model.UserStatus;
import com.miu.estate.userservice.service.JwtService;
import com.miu.estate.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User userLogin = userService.findUserByEmail(loginRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found!"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), userLogin.getPassword())) {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body("Invalid password!");
        }
        return ResponseEntity.ok(jwtService.generateToken(userLogin));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest userRequest) {
        User user = userRequest.getUserEntity();
        User createdUser = userService.createUser(user).orElseThrow(() -> new RuntimeException("Bad request!"));
        RegisterUserResponse userResponse = RegisterUserResponse.fromUserEntity(createdUser);
        userResponse.setVerificationCode(jwtService.generateVerificationToken(userResponse.getEmail()));
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/register/{userId}/verification")
    public ResponseEntity<?> verifyUser(@PathVariable Long userId, @RequestParam String verificationCode) {
        User registerUser = userService.getUserById(userId).orElse(null);
        if (registerUser == null) {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("User not found!");
        }
        String email = jwtService.extractUsername(verificationCode);

        if (!registerUser.getEmail().equals(email)) {
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body("Invalid verification code!");
        }

        registerUser.setStatus(UserStatus.ACTIVATED);
        userService.updateUser(userId, registerUser);
        return ResponseEntity.ok().body(UserDetailResponse.fromUserEntity(registerUser));
    }
}
