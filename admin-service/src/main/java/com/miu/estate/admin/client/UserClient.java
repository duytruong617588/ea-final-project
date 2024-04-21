package com.miu.estate.admin.client;

import com.ttd.core.model.User;
import com.ttd.core.model.propertypaging.PropertyPaging;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@FeignClient(value = "user-service")
public interface UserClient {
	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/users/list")
	List<User> getAll();

	User changeUserStatus(Long id, String status);

	User setProfileApproval(Long id, Boolean isApprove);

	User resetUserPassword(Long id, String newPassword);
}