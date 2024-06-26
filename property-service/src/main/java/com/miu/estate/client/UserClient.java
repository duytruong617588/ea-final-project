package com.miu.estate.client;

import com.ttd.core.model.User;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "user-service")
public interface UserClient {
	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/users/list")
	List<User> getUsers(@RequestHeader("Authorization") String token);

	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/users/all")
	List<User> getAllUsers();

	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/users/verification")
	User getUserByToken(@RequestHeader("Authorization") String token);

	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/users/detail/{id}")
	User getUserDetailById(@PathVariable Long id);
}