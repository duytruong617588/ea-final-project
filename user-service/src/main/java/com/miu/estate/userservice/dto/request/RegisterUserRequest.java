package com.miu.estate.userservice.dto.request;

import com.miu.estate.userservice.model.User;
import com.miu.estate.userservice.model.UserRole;
import com.miu.estate.userservice.model.UserStatus;
import com.miu.estate.userservice.utils.Constant;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterUserRequest {
    @NotNull(message = "Email cannot empty")
    @Pattern(regexp = Constant.EmailRegex, message = "Email is not valid")
    private String email;

    @NotNull(message = "password cannot empty")
    @Size(min = 8, message = "Password needs at least 8 characters")
    private String password;

    private String firstName;
    private String lastName;

    private String phone;

    private UserRole role;

    private AddressRequest address;

    private String verificationCode;

    public User getUserEntity() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);

        user.setRole(role != null ? role : UserRole.USER);
        user.setStatus(UserStatus.PENDING);

        user.setAddress(address.getAddressEntity());

        return user;
    }
}
