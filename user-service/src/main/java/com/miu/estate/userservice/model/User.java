package com.miu.estate.userservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Setter
@Getter
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    @NonNull
    @Column(unique = true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Boolean isApproved = false;
    private Boolean active = true;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
}
