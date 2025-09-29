package com.sonnguyen.common.client;

import com.sonnguyen.common.model.infrastructure.exception.Error;

public enum BadRequestError implements Error {
    IAM_SERVICE_NOT_AVAILABLE("4000001", "IAM Service not available"),
    STORAGE_SERVICE_NOT_AVAILABLE("4000001", "Storage Service not available"),
    NOTIFICATION_SERVICE_NOT_AVAILABLE("4000001", "Notification Service not available"),
    INVALID_AUTHENTICATION("4000001", "Invalid authentication value"),
    ;

    private String code;
    private String message;

    BadRequestError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return this.name();
    }
}
