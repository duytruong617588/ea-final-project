package com.miu.estate.userservice.dto.request;

import com.miu.estate.userservice.model.User;
import com.miu.estate.userservice.model.UserRole;
import com.miu.estate.userservice.model.UserStatus;
import com.miu.estate.userservice.utils.Constant;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CreateUserRequest {
    @NotNull(message = "Email cannot empty")
    @Pattern(regexp = Constant.EmailRegex, message = "Email is not valid")
    private String email;

    @NotNull(message = "password cannot empty")
    @Size(min = 8, message = "Password needs at least 8 characters")
    private String password;

    @NotNull(message = "firstName cannot empty")
    private String firstName;
    private String lastName;

    @NotNull(message = "phone cannot empty")
    private String phone;

    @NotNull(message = "role cannot empty")
    private UserRole role;

    @NotNull(message = "status cannot empty")
    private UserStatus status;

    @NotNull(message = "address cannot empty")
    private AddressRequest address;

    public User getUserEntity() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setRole(role);
        user.setStatus(status);
        user.setAddress(address.getAddressEntity());

        return user;
    }
}
