package com.sonnguyen.common.web.configuration.impl;

import com.sonnguyen.common.model.application.response.Response;
import com.sonnguyen.common.model.infrastructure.constant.HttpStatus;
import com.sonnguyen.common.model.infrastructure.exception.ResponseException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.module.ResolutionException;

@ControllerAdvice
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    MessageSource messageSource;

    @ExceptionHandler({ResolutionException.class})
    public ResponseEntity<Response<Void>> handleResponseException(ResponseException exception) {
        exception.printStackTrace();
        Response<Void> response = new Response<Void>(null,
                exception.getMessage(),
                exception.getCode(),
                exception.getStatus(),
                exception, false);
        return ResponseEntity.status(exception.getStatus())
                .body(response);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Response<Void>> handleResponseException(Exception exception) {
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new Response<Void>(null,
                        exception.getMessage(),
                        String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR),
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        exception, false));
    }
}
