package com.miu.estate.exception;

import java.util.function.Supplier;

public class PropertyReviewNotFoundException extends RuntimeException implements
		Supplier<PropertyReviewNotFoundException> {
	public PropertyReviewNotFoundException(String message) {
		super(message);
	}

	public PropertyReviewNotFoundException() {
		super("Review of Property is not found!");
	}

	@Override
	public PropertyReviewNotFoundException get() {
		return this;
	}
}
