package com.miu.estate.exception;

import java.util.function.Supplier;

public class PublishStatusPropertyInvalidException extends RuntimeException implements Supplier<PublishStatusPropertyInvalidException> {
	public PublishStatusPropertyInvalidException(String message) {
		super(message);
	}
	public PublishStatusPropertyInvalidException() {
		super("Publish status of property invalid!");
	}
	@Override
	public PublishStatusPropertyInvalidException get() {
		return this;
	}
}
