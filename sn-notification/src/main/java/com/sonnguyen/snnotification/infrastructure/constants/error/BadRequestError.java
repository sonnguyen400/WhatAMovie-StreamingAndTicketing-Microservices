package com.sonnguyen.snnotification.infrastructure.constants.error;

import com.sonnguyen.common.model.infrastructure.exception.Error;

public enum BadRequestError implements Error {
    SCHEDULED_SEND_NOTIFICATION_ERROR("4003000", "Send notification Fail");

    private final String name;
    private final String code;

    BadRequestError(String code, String name) {
        this.name = name;
        this.code = code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getCode() {
        return this.code;
    }
}
