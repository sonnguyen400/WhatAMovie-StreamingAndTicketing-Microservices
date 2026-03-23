package com.sonnguyen.snaddress.domain.query;

import com.sonnguyen.common.model.domain.PagingSearchQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdministrativeRegionSearchQuery extends PagingSearchQuery {
    private UUID countryId;
    private UUID parentId;
    private String keyword;
    private String type;
    private Integer level;
}
