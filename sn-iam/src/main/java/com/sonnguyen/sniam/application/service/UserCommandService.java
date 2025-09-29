package com.sonnguyen.sniam.application.service;


import com.sonnguyen.sniam.application.dto.request.LoginPasswordRequest;
import com.sonnguyen.sniam.application.dto.request.RegisterUserRequest;
import com.sonnguyen.sniam.application.dto.response.AccessTokenResponse;

public interface UserCommandService {
    void registerUser(RegisterUserRequest request);

    AccessTokenResponse loginPassword(LoginPasswordRequest request);
}
