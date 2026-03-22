package com.sonnguyen.common.model.application.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class PagingRequest extends Request {
    private Long pageSize;
    private Long pageIndex;
    private List<SortOrder> sortBy;
}
