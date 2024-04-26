package com.miu.estate.aspect;

import com.miu.estate.exception.TokenVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RequiredArgsConstructor
public class TokenVerificationAdvice extends ResponseEntityExceptionHandler {

    @Pointcut("within(com.miu.estate.controller.*.*)")
    public void allControllerMethods() {
        System.out.println("All controller methods");
    }

    private final HttpServletRequest request;

    @Before("allControllerMethods()")
    public void validateToken() {
        String token = request.getHeader("Authorization");
        System.out.println("Token: " + token);
        if (token == null || !isValidToken(token)) {
            throw new TokenVerificationException("Invalid or missing token");
        }
    }

    private boolean isValidToken(String token) {
        return true;
    }
}
