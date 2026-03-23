package com.sonnguyen.snaddress.domain.query;

import com.sonnguyen.common.model.domain.PagingSearchQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountrySearchQuery extends PagingSearchQuery {
    private String keyword;
}
