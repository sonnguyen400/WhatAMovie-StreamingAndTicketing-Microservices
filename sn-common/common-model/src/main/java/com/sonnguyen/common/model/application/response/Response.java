package com.sonnguyen.common.model.application.response;

import com.sonnguyen.common.model.infrastructure.constant.HttpStatus;
import com.sonnguyen.common.model.infrastructure.exception.Error;
import com.sonnguyen.common.model.infrastructure.exception.ResponseException;
import com.sonnguyen.common.util.StrUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Response<T> implements Serializable {
    protected T data;
    protected String message;
    protected String code;
    protected int status;
    protected Exception exception;
    protected Boolean isSuccess;

    public Response() {
    }

    public Response(T data, String message, String code, int status) {
        this.data = data;
        this.message = message;
        this.code = code;
        this.status = status;
        this.isSuccess = true;
    }

    public static <T> Response<T> of(T data, String message, String code) {
        return new Response<>(data, message, code, HttpStatus.OK);
    }

    public static <T> Response<T> of(T data) {
        return new Response<>(data, StrUtils.EMPTY, StrUtils.EMPTY, HttpStatus.OK);
    }

    public static Response<Void> ok() {
        Response<Void> response = new Response<>();
        response.status = HttpStatus.OK;
        response.isSuccess = true;
        return response;
    }

    public static <Void> Response<Void> fail(String message, String code, int status, Exception exception) {
        return new Response<>(null, message, code, status);
    }

    public static <Void> Response<Void> fail(String message) {
        return Response.fail(message, StrUtils.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR, null);
    }


    public static <Void> Response<Void> fail() {
        return Response.fail(StrUtils.EMPTY, StrUtils.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    public static <Void> Response<Void> fail(Error error) {
        return Response.fail(error.getMessage(), StrUtils.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR, new ResponseException(error));
    }

    public Boolean isSuccess() {
        return isSuccess;
    }
}
