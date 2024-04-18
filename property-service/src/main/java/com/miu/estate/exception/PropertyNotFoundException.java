package com.miu.estate.exception;

import java.util.function.Supplier;

public class PropertyNotFoundException extends RuntimeException implements Supplier<PropertyNotFoundException> {
	public PropertyNotFoundException(String message) {
		super(message);
	}

	public PropertyNotFoundException() {
		super("Property not found!");
	}

	@Override
	public PropertyNotFoundException get() {
		return this;
	}
}
