package com.sonnguyen.common.web.security;

public interface UserPasswordEncoder {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
