package com.sonnguyen.snultility.domain.query;

import com.sonnguyen.common.model.domain.PagingSearchQuery;
import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import lombok.Data;

@Data
public class TagSearchQuery extends PagingSearchQuery {
    private String keyword;
    private LocaleCode localeCode;
}
