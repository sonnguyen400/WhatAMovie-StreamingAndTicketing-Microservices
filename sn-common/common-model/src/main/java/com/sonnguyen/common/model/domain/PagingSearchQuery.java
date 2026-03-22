package com.sonnguyen.common.model.domain;


import com.sonnguyen.common.model.application.request.SortOrder;
import com.sonnguyen.common.model.domain.query.Query;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder(builderMethodName = "queryBuilder")
public abstract class PagingSearchQuery extends Query {
    private Long pageSize;
    private Long pageIndex;
    private List<SortOrder> sortBy;

    public PagingSearchQuery() {
    }

    public PagingSearchQuery(Long pageSize, Long pageIndex, List<SortOrder> sortBy) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.sortBy = sortBy;
    }
}
