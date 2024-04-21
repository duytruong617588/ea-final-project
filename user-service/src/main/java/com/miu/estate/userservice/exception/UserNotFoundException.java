package com.miu.estate.userservice.exception;

import java.util.function.Supplier;

public class UserNotFoundException extends RuntimeException implements Supplier<UserNotFoundException> {
	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException() {
		super("User not found!");
	}
	@Override
	public UserNotFoundException get() {
		return this;
	}
}
