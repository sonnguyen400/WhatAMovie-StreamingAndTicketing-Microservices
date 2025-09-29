package com.sonnguyen.sniam.infrastructure.configuration;

import com.sonnguyen.common.web.security.UserPasswordEncoder;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordEncoderImpl implements UserPasswordEncoder {
    private Argon2PasswordEncoder passwordEncoder;

    public UserPasswordEncoderImpl() {
        this.passwordEncoder = new Argon2PasswordEncoder(16,32,1, 16384,2);
    }

    @Override
    public String encode(String rawPassword) {
        return this.passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return this.passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
