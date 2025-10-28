package com.sonnguyen.sncatalogue.application.dto.request;

import com.sonnguyen.common.model.application.request.Request;
import com.sonnguyen.sncatalogue.domain.command.CatalogMessageLocaleCreateCmd;
import com.sonnguyen.sncatalogue.domain.command.CatalogPartnerCreateOrUpdateCmd;
import com.sonnguyen.sncatalogue.infrastructure.constant.ContentStatus;
import com.sonnguyen.sncatalogue.infrastructure.constant.DistributionChannel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CatalogItemCreateRequest extends Request {
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
