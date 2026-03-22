package com.sonnguyen.sncatalogue.application.dto.request;

import com.sonnguyen.common.model.application.request.PagingRequest;
import com.sonnguyen.sncatalogue.infrastructure.constant.ContentStatus;
import com.sonnguyen.sncatalogue.infrastructure.constant.ContentType;
import com.sonnguyen.sncatalogue.infrastructure.constant.DistributionChannel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CatalogItemSearchRequest extends PagingRequest {
    private String keyword;
    private List<UUID> tagIds;
    private List<ContentType> contentTypes;
    private List<ContentStatus> contentStatuses;
    private List<DistributionChannel> channels;
    private UUID parentIds;
}
