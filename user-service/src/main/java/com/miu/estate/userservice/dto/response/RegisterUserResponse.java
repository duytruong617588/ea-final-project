package com.miu.estate.userservice.dto.response;

import com.miu.estate.userservice.model.Address;
import com.miu.estate.userservice.model.User;
import com.miu.estate.userservice.model.UserRole;
import com.miu.estate.userservice.model.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class RegisterUserResponse {
    private String verificationCode;
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Boolean isApproved;
    private Boolean active;
    private UserRole role;
    private UserStatus status;
    private Address address;

    public RegisterUserResponse(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String email, String firstName, String lastName, String phone, Boolean isApproved, Boolean active, UserRole role, UserStatus status, Address address) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.isApproved = isApproved;
        this.active = active;
        this.role = role;
        this.status = status;
        this.address = address;
    }

    public static RegisterUserResponse fromUserEntity(User user) {
        return new RegisterUserResponse(
                user.getId(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getIsApproved(),
                user.getActive(),
                user.getRole(),
                user.getStatus(),
                user.getAddress()
        );
    }
}
