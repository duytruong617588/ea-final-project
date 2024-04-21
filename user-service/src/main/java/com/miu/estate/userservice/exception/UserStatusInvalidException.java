package com.miu.estate.userservice.exception;

import java.util.function.Supplier;

public class UserStatusInvalidException extends RuntimeException implements Supplier<UserStatusInvalidException> {
	public UserStatusInvalidException(String message) {
		super(message);
	}

	public UserStatusInvalidException() {
		super("User status invalid!");
	}
	@Override
	public UserStatusInvalidException get() {
		return this;
	}
}
