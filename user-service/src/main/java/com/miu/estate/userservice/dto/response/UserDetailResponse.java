package com.miu.estate.userservice.dto.response;

import com.miu.estate.userservice.model.UserRole;
import com.miu.estate.userservice.model.UserStatus;
import com.miu.estate.userservice.model.Address;
import com.miu.estate.userservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UserDetailResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Boolean isApproved;
    private Boolean active;
    private UserRole role;
    private UserStatus status;
    private Address address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public static UserDetailResponse fromUserEntity(User user) {
        return new UserDetailResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getIsApproved(),
                user.getActive(),
                user.getRole(),
                user.getStatus(),
                user.getAddress(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
