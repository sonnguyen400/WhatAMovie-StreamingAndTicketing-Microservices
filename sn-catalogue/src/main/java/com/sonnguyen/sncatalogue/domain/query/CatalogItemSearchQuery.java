package com.sonnguyen.sncatalogue.domain.query;

import com.sonnguyen.common.model.domain.PagingSearchQuery;
import com.sonnguyen.sncatalogue.infrastructure.constant.ContentStatus;
import com.sonnguyen.sncatalogue.infrastructure.constant.ContentType;
import com.sonnguyen.sncatalogue.infrastructure.constant.DistributionChannel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class CatalogItemSearchQuery extends PagingSearchQuery {
    private String keyword;
    private List<UUID> tagIds;
    private List<ContentType> contentTypes;
    private List<ContentStatus> contentStatuses;
    private List<DistributionChannel> channels;
    private UUID parentId;
}
