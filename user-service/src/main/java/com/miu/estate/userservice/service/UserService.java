package com.miu.estate.userservice.service;


import com.miu.estate.userservice.client.PropertyClient;
import com.miu.estate.userservice.exception.UserNotFoundException;
import com.miu.estate.userservice.exception.UserStatusInvalidException;
import com.miu.estate.userservice.model.User;
import com.miu.estate.userservice.model.UserRole;
import com.miu.estate.userservice.model.UserStatus;
import com.miu.estate.userservice.repository.UserRepository;
import com.ttd.core.model.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PropertyClient propertyClient;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<Property> getListProperties() {
        return propertyClient.getListProperties().getContent();
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userRepository.findByIdAndDeletedAtIsNull(id));
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmailAndDeletedAtIsNull(email);
    }

    public Optional<User> createUser(User u) {
        String encodedPassword = passwordEncoder.encode(u.getPassword());
        u.setPassword(encodedPassword);
        return Optional.ofNullable(userRepository.save(u));
    }

    public Optional<User> updateUser(Long id, User user) {
        return userRepository.findById(id).map( foundUser -> {
            user.setId(id);
            return userRepository.save(user);
        });
    }

    public void deleteUser(Long id) {
        userRepository.softDeleteUserById(id);
    }

    public Optional<List<User>> searchUsers(String keyword, UserRole userRole, UserStatus userStatus) {
        if (userStatus == null) userStatus = UserStatus.ACTIVATED;
        return Optional.ofNullable(userRepository.searchUsersByCondition(keyword, userRole, userStatus));
    }

    public User resetUserPassword(Long userId, String newPassword) {
        var user = userRepository.findById(userId).orElseThrow(new UserNotFoundException());
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public User changeUserStatus(Long id, String status) {
        var user = userRepository.findById(id).orElseThrow(new UserNotFoundException());
        var userStatus = UserStatus.find(status).orElseThrow(new UserStatusInvalidException());
        user.setStatus(userStatus);
        return 	userRepository.save(user);
    }

    public User setProfileApproval(Long id, Boolean isApprove) {
        var user = userRepository.findById(id).orElseThrow(new UserNotFoundException());
        user.setIsApproved(isApprove);
        return userRepository.save(user);
    }
}
