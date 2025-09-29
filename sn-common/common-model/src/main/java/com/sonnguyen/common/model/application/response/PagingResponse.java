package com.sonnguyen.common.model.application.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class PagingResponse<T> extends Response<List<T>> {
    private int total;
    private int pageSize;
    private int pageIndex;

    public PagingResponse() {
    }

    public static <T> PagingResponseBuilder<List<T>> of(List<T> data) {
        return new PagingResponseBuilder<List<T>>()
                .data(data);
    }

    public static class PagingResponseBuilder<T> {
        private int total;
        private int pageSize;
        private int pageIndex;
        private T data;
        private String message;
        private String code;
        private int status;
        private Exception exception;
        private Boolean isSuccess;

        public static <T> PagingResponseBuilder<T> of(T data) {
            return new PagingResponseBuilder<T>()
                    .data(data);
        }

        public PagingResponseBuilder<T> data(T data) {
            this.data = data;
            return this;
        }


        public PagingResponseBuilder<T> message(String message) {
            this.message = message;
            return this;
        }


        public PagingResponseBuilder<T> code(String code) {
            this.code = code;
            return this;
        }

        public PagingResponseBuilder<T> status(int status) {
            this.status = status;
            return this;
        }

        public PagingResponseBuilder<T> exception(Exception exception) {
            this.exception = exception;
            return this;
        }

        public PagingResponseBuilder<T> isSuccess(Boolean success) {
            this.isSuccess = success;
            return this;
        }

        public PagingResponseBuilder<T> total(int total) {
            this.total = total;
            return this;
        }

        public PagingResponseBuilder<T> pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public PagingResponseBuilder<T> pageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
            return this;
        }

        public PagingResponse<T> build() {
            return new PagingResponse<>();
        }
    }
}
