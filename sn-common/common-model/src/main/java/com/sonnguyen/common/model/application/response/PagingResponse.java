package com.sonnguyen.common.model.application.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@SuperBuilder(toBuilder = true, builderMethodName = "pagingBuilder")
public class PagingResponse<T> extends Response<Collection<? extends T>> {
    private Long total;
    private Long pageSize;
    private Long pageIndex;

    public PagingResponse() {
    }

    public static <T> PagingResponse<T> of(Collection<? extends T> data, Long total, Long pageSize, Long pageIndex) {
        PagingResponse<T> pagingResponse = PagingResponse
                .<T>pagingBuilder()
                .data(data)
                .total(total)
                .pageSize(pageSize)
                .pageIndex(pageIndex)
                .build();
        return pagingResponse;
    }
}
