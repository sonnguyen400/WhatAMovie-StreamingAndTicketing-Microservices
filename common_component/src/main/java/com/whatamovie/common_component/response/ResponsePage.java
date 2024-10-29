package com.whatamovie.common_component.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponsePage<T> {
    private int page;
    private int total;
    private long totalElements;
    private List<T> contents;

}
