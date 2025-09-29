package com.sonnguyen.sniam.presentation.rest.impl;

import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.common.model.application.security.UserAuthority;
import com.sonnguyen.common.web.security.SecurityUtils;
import com.sonnguyen.sniam.application.dto.request.LoginPasswordRequest;
import com.sonnguyen.sniam.application.dto.request.RegisterUserRequest;
import com.sonnguyen.sniam.application.dto.response.AccessTokenResponse;
import com.sonnguyen.sniam.application.service.UserCommandService;
import com.sonnguyen.sniam.application.service.UserQueryService;
import com.sonnguyen.sniam.presentation.rest.UserController;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserControllerImpl implements UserController {
    UserCommandService userCommandService;
    UserQueryService userQueryService;

    @Override
    public AccessTokenResponse login(LoginPasswordRequest request) {
        return this.userCommandService.loginPassword(request);
    }

    @Override
    public Response<Void> register(RegisterUserRequest request) {
        this.userCommandService.registerUser(request);
        return Response.ok();
    }

    @Override
    public Response<UserAuthority> getCurrentUserAuthority() {
        UUID userId = SecurityUtils.getCurrentUserId();
        UserAuthority userAuthority = this.userQueryService.getUserAuthority(userId);
        return Response.of(userAuthority);
    }

    @Override
    public Response<UserAuthority> getUserAuthority(UUID userId) {
        UserAuthority userAuthority = this.userQueryService.getUserAuthority(userId);
        return Response.of(userAuthority);
    }
}
