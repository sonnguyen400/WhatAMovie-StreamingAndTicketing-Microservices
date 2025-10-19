package com.sonnguyen.common.model.infrastructure.exception;

import com.sonnguyen.common.model.infrastructure.constant.HttpStatus;

public interface Error {
    public static String UNKNOWN_ERROR = "Unknown error";
    public static String SUCCESS = "Success";
    public static String DEFAULT_ERROR_CODE = "4000000";

    default String getMessage() {
        return UNKNOWN_ERROR;
    }

    default String getCode() {
        return DEFAULT_ERROR_CODE;
    }

    default int getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    String getName();
}
