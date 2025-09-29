package com.sonnguyen.sniam.infrastructure.error;

import com.sonnguyen.common.model.infrastructure.constant.HttpStatus;
import com.sonnguyen.common.model.infrastructure.exception.Error;

public enum NotFoundError implements Error {
    USER_NOT_FOUND("4000004", "User not found"),
    USER_NAME_NOT_FOUND("4000005", "Username, email or phone number not found"),
    ;
    private final String message;
    private final String code;

    NotFoundError(String code, String message) {
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
    public int getStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getName() {
        return this.name();
    }
}
