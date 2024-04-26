package com.miu.estate.aspect;

import com.miu.estate.exception.TokenVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class TokenVerificationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(TokenVerificationException.class)
    public ResponseEntity<String> handleTokenVerificationException(TokenVerificationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
