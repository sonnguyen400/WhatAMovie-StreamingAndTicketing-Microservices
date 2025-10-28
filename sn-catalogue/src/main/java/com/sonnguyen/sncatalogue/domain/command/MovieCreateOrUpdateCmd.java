package com.sonnguyen.sncatalogue.domain.command;

import com.sonnguyen.sncatalogue.infrastructure.constant.ContentStatus;
import com.sonnguyen.sncatalogue.infrastructure.constant.ContentType;
import com.sonnguyen.sncatalogue.infrastructure.constant.DistributionChannel;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class MovieCreateOrUpdateCmd extends CatalogItemCreateOrUpdateCmd{
    private Integer releaseYear;
    private Duration duration;
    private UUID parentId;

    private List<CatalogMessageLocaleCreateCmd> messageLocales;
    private List<CatalogPartnerCreateOrUpdateCmd> partners;
    private List<UUID> tagIds;
}
