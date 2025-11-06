package com.sonnguyen.common.model.infrastructure.exception;

import com.sonnguyen.common.model.infrastructure.support.constant.HttpStatus;

public class ResponseException extends RuntimeException {
    private int status;
    private String code;
    private String name;
    private Exception cause;

    public ResponseException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
        this.name = message;
    }

    public ResponseException(Error error) {
        super(error.getMessage());
        this.status = error.getStatus();
        this.code = error.getCode();
        this.name = error.getName();
    }

    public ResponseException(Error error, Exception cause) {
        super(error.getMessage());
        this.status = error.getStatus();
        this.code = error.getCode();
        this.name = error.getName();
        this.cause = cause;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
}
