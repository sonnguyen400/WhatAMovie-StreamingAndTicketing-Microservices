package com.sonnguyen.sniam.infrastructure.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserPasswordEncoderImplTest {

    private UserPasswordEncoderImpl passwordEncoder;

    @BeforeEach
    void setUp() {
        passwordEncoder = new UserPasswordEncoderImpl();
    }

    @Test
    void encode_shouldReturnEncodedPassword() {
        String rawPassword = "123456aA@";

        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.append(encodedPassword);
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(encodedPassword.startsWith("$argon2"));
    }

    @Test
    void encode_samePasswordMultipleTimes_shouldReturnDifferentHashes() {
        String rawPassword = "mySecretPassword123";

        String encodedPassword1 = passwordEncoder.encode(rawPassword);
        String encodedPassword2 = passwordEncoder.encode(rawPassword);

        assertNotEquals(encodedPassword1, encodedPassword2); // Argon2 generates a random salt each time
    }

    @Test
    void matches_withCorrectPassword_shouldReturnTrue() {
        String rawPassword = "mySecretPassword123";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);

        assertTrue(matches);
    }

    @Test
    void matches_withIncorrectPassword_shouldReturnFalse() {
        String rawPassword = "mySecretPassword123";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        boolean matches = passwordEncoder.matches("wrongPassword", encodedPassword);

        assertFalse(matches);
    }
}
