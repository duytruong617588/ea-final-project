package com.miu.estate.admin.client;

import com.miu.estate.admin.model.ResetPasswordRequest;
import com.ttd.core.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@FeignClient(value = "user-service")
public interface UserClient {
	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/users/list")
	List<User> getAll();

	//
	@RequestMapping(method = RequestMethod.PUT, value = "/api/v1/users/{id}/active/{status}")
	User changeUserStatus(@PathVariable Long id, @PathVariable String status);

	@RequestMapping(method = RequestMethod.PUT, value = "/api/v1/users/{id}/approval/{isApprove}")
	User setProfileApproval( @PathVariable Long id, @PathVariable Boolean isApprove);

	@RequestMapping(method = RequestMethod.PUT, value = "/api/v1/users/{id}/reset/password")
	User resetUserPassword(@PathVariable Long id, @RequestBody ResetPasswordRequest newPassword);

}