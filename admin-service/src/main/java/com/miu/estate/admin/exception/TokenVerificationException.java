package com.miu.estate.admin.exception;


public class TokenVerificationException extends RuntimeException {
	public TokenVerificationException(String message) {
		super(message);
	}
}