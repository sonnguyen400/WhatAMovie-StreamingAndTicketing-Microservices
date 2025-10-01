package com.sonnguyen.sniam.application.service.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.sonnguyen.common.model.application.security.UserAuthority;
import com.sonnguyen.common.model.infrastructure.constant.DefaultClaim;
import com.sonnguyen.common.model.infrastructure.exception.ResponseException;
import com.sonnguyen.common.util.StrUtils;
import com.sonnguyen.common.web.security.UserAuthentication;
import com.sonnguyen.common.web.security.UserPasswordEncoder;
import com.sonnguyen.sniam.application.dto.request.LoginPasswordRequest;
import com.sonnguyen.sniam.application.dto.request.RegisterUserRequest;
import com.sonnguyen.sniam.application.dto.response.AccessTokenResponse;
import com.sonnguyen.sniam.application.mapper.IamCommandMapper;
import com.sonnguyen.sniam.application.service.UserCommandService;
import com.sonnguyen.sniam.domain.User;
import com.sonnguyen.sniam.domain.command.UserCreateOrUpdateCmd;
import com.sonnguyen.sniam.domain.repository.UserRepository;
import com.sonnguyen.sniam.infrastructure.configuration.ApplicationConfiguration;
import com.sonnguyen.sniam.infrastructure.configuration.AuthenticationService;
import com.sonnguyen.sniam.infrastructure.configuration.JWTProvider;
import com.sonnguyen.sniam.infrastructure.error.BadRequestError;
import com.sonnguyen.sniam.infrastructure.persistence.repository.UserEntityRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserCommandServiceImpl implements UserCommandService {
    UserEntityRepository userEntityRepository;
    UserRepository userRepository;
    IamCommandMapper iamCommandMapper;
    UserPasswordEncoder passwordEncoder;
    JWTProvider jwtProvider;
    ApplicationConfiguration applicationConfiguration;
    AuthenticationService authenticationService;
    DataSource source;

    @Override
    public void registerUser(RegisterUserRequest request) {
        UserCreateOrUpdateCmd userCreateOrUpdateCmd = this.iamCommandMapper.from(request);
        this.userEntityRepository.findDuplicated(
                userCreateOrUpdateCmd.getUsername(),
                userCreateOrUpdateCmd.getEmail(),
                userCreateOrUpdateCmd.getPhoneNumber()
        ).ifPresent(u -> {
            throw new ResponseException(BadRequestError.USER_DUPLICATED);
        });
        User user = new User(userCreateOrUpdateCmd, List.of(), passwordEncoder);
        this.userRepository.save(user);
    }

    @Override
    public AccessTokenResponse loginPassword(LoginPasswordRequest request) {
        UserAuthentication user = this.authenticationService.authenticate(request.getUsername(), request.getPassword());
        SecurityContextHolder.getContext().setAuthentication(user);
        UserAuthority userAuthority = (UserAuthority) user.getPrincipal();
        String accessToken = StrUtils.EMPTY;
        try {
            accessToken = this.jwtProvider.builder()
                    .algorithm(JWSAlgorithm.RS256)
                    .addClaims(DefaultClaim.USERID, userAuthority.getUserId())
                    .addClaims(DefaultClaim.EMAIL, userAuthority.getEmail())
                    .addClaims(DefaultClaim.USERNAME, userAuthority.getUserName())
                    .expirationAfter(this.applicationConfiguration.getSecurity().getAccessTokenExpireTime())
                    .issuer(this.applicationConfiguration.getSecurity().getJwtIssuer())
                    .subject(userAuthority.getUserId().toString())
                    .build();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return AccessTokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
