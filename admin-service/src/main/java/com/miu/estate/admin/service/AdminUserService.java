package com.miu.estate.admin.service;

import com.miu.estate.admin.client.UserClient;
import com.miu.estate.admin.model.ResetPasswordRequest;
import com.ttd.core.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserService {
	private final UserClient userClient;

	public List<User> getAll() {
		return userClient.getAll();
	}

	public User resetUserPassword(Long id, ResetPasswordRequest newPassword) {
		return userClient.resetUserPassword(id, newPassword);
	}

	public User changeUserStatus(Long id, String status) {
		return userClient.changeUserStatus(id, status);
	}

	public User setProfileApproval(Long id, Boolean isApprove) {
		return userClient.setProfileApproval(id, isApprove);
	}
}
