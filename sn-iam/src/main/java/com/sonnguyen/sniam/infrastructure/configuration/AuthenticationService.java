package com.sonnguyen.sniam.infrastructure.configuration;

import com.sonnguyen.common.model.application.security.UserAuthority;
import com.sonnguyen.common.model.infrastructure.exception.ResponseException;
import com.sonnguyen.common.web.security.UserAuthentication;
import com.sonnguyen.common.web.security.UserPasswordEncoder;
import com.sonnguyen.sniam.domain.User;
import com.sonnguyen.sniam.domain.repository.UserRepository;
import com.sonnguyen.sniam.infrastructure.error.NotFoundError;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    UserPasswordEncoder passwordEncoder;

    public UserAuthentication authenticate(String username, String password) {
        Optional<User> userOpt = this.userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new ResponseException(NotFoundError.USER_NAME_NOT_FOUND);
        }
        User user = userOpt.get();
        UserAuthority userAuthority = user.login(password, this.passwordEncoder);
        return new UserAuthentication(userAuthority, null);
    }
}
