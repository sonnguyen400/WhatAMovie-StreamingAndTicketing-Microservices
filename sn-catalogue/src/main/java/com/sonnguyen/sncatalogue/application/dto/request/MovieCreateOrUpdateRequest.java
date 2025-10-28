package com.sonnguyen.sncatalogue.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class MovieCreateOrUpdateRequest extends CatalogItemCreateRequest {
    private Integer releaseYear;
    private Duration duration;
    private UUID parentId;

    private List<CatalogMessageLocaleCreateRequest> messageLocales;
    private List<CatalogPartnerCreateOrUpdateRequest> partners;
    private List<UUID> tagIds;
}
