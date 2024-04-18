package com.miu.estate.userservice.repository;


import com.miu.estate.userservice.model.User;
import com.miu.estate.userservice.model.UserRole;
import com.miu.estate.userservice.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?1", nativeQuery = true)
    void softDeleteUserById(Long id);

    User findByIdAndDeletedAtIsNull(Long id);
    Optional<User> findByEmailAndDeletedAtIsNull(String email);

    List<User> getUserByRoleAndDeletedAtIsNull(UserRole role);

    @Query("SELECT u FROM users u WHERE (:keyword IS NULL OR u.email LIKE %:keyword% OR u.firstName LIKE %:keyword% OR u.lastName LIKE %:keyword%) " +
            "AND (:userRole IS NULL OR u.role = :userRole) " +
            "AND (:userStatus IS NULL OR u.status = :userStatus)" +
            "AND u.deletedAt IS NULL")
    List<User> searchUsersByCondition(
            @Param("keyword") String keyword,
            @Param("userRole") UserRole userRole,
            @Param("userStatus") UserStatus userStatus
    );
}
