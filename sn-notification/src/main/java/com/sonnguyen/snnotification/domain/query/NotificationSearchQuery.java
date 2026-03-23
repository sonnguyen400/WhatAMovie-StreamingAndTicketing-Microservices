package com.sonnguyen.snnotification.domain.query;

import com.sonnguyen.common.model.domain.PagingSearchQuery;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationSearchQuery extends PagingSearchQuery {
    private Boolean hasReaded;
}
