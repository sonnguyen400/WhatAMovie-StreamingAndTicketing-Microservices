package com.sonnguyen.sncatalogue.domain.command;

import com.sonnguyen.sncatalogue.domain.CatalogMetadata;
import com.sonnguyen.sncatalogue.infrastructure.constant.ContentStatus;
import com.sonnguyen.sncatalogue.infrastructure.constant.ContentType;
import com.sonnguyen.sncatalogue.infrastructure.constant.DistributionChannel;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class CatalogItemCreateOrUpdateCmd {
    private String title;
    private String description;
    private String slug;
    private ContentStatus status;
    private DistributionChannel distributionChannel;
    private String synopsis;
    private UUID posterFileId;
    private UUID backdropId;
    private UUID trainerId;
    private UUID parentId;

    private List<CatalogMessageLocaleCreateCmd> messageLocales;
    private List<CatalogPartnerCreateOrUpdateCmd> partners;
    private List<UUID> tagIds;
}
