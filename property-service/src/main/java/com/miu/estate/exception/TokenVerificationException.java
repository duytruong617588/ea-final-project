package com.miu.estate.exception;


public class TokenVerificationException extends RuntimeException {
	public TokenVerificationException(String message) {
		super(message);
	}
}