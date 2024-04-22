package com.miu.estate.userservice.dto.request;

import com.miu.estate.userservice.model.User;
import com.miu.estate.userservice.model.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class UpdateUserRequest {
    @NotNull(message = "firstName cannot empty")
    private String firstName;
    private String lastName;

    @NotNull(message = "phone cannot empty")
    private String phone;

    @NotNull(message = "role cannot empty")
    private UserRole role;

    @NotNull(message = "address cannot empty")
    private AddressRequest address;

    public User getUserEntity() {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setRole(role);
        user.setAddress(address.getAddressEntity());
        return user;
    }
}
