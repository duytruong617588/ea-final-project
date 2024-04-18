package com.miu.estate.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ServiceUtil {
    public static String encodePassword(String password) {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        return bc.encode(password);
    }
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        return bc.matches(rawPassword, encodedPassword);
    }
}
