package com.sonnguyen.sniam.infrastructure.error;

import com.sonnguyen.common.model.infrastructure.exception.Error;

public enum BadRequestError implements Error {
    BAD_REQUEST_ERROR("4000001", "Bad request"),
    INVALID_USER_NAME_PASSWORD("4000002", "Invalid username or password"),
    LOCKED_USER("4000003", "Locked user"),
    UNVERIFIED_USER("4000004", "Unverified user"),
    CONSECUTIVE_LOGIN_FAIL("4000005", "Consecutive login failures"),
    USER_DUPLICATED("4000005","Email, Username or phone number has already existed" ),
    INVALID_AUTHENTICATION("4000005","Invalid authentication" );

    private final String message;
    private final String code;

    BadRequestError(String code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name();
    }


}
