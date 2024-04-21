package com.miu.estate.client;

import com.ttd.core.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "user-service")
public interface UserClient {
	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/users/list")
	List<User> getUsers();

	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/users/verification")
	User getUserByToken();

	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/users/{id}")
	User getUserById(Long id);
}