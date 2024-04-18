package com.miu.estate.userservice.service;


import com.miu.estate.userservice.model.User;
import com.miu.estate.userservice.model.UserRole;
import com.miu.estate.userservice.model.UserStatus;
import com.miu.estate.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userRepository.findByIdAndDeletedAtIsNull(id));
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmailAndDeletedAtIsNull(email);
    }

    public Optional<User> createUser(User u) {
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
}
