package com.sonnguyen.sniam.presentation.rest;

import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.common.model.application.security.UserAuthority;
import com.sonnguyen.sniam.application.dto.request.LoginPasswordRequest;
import com.sonnguyen.sniam.application.dto.request.RegisterUserRequest;
import com.sonnguyen.sniam.application.dto.response.AccessTokenResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/api")
@Validated
public interface UserController {

    @PostMapping("/v1/authentication/login-password")
    AccessTokenResponse login(@RequestBody LoginPasswordRequest request);

    @PostMapping("/v1/authentication/register")
    Response<Void> register(@RequestBody RegisterUserRequest request);

    @GetMapping("/v1/me/authorities")
    Response<UserAuthority> getCurrentUserAuthority();

    @GetMapping("/v1/authorities/{userId}")
    Response<UserAuthority> getUserAuthority(@PathVariable("userId") UUID userId);
}
