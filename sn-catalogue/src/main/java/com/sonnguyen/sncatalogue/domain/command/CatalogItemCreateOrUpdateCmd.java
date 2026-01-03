package com.sonnguyen.sncatalogue.domain.command;

import com.sonnguyen.common.model.domain.command.InternationalizationCmd;
import com.sonnguyen.sncatalogue.infrastructure.constant.ContentStatus;
import com.sonnguyen.sncatalogue.infrastructure.constant.ContentType;
import com.sonnguyen.sncatalogue.infrastructure.constant.DistributionChannel;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
public class CatalogItemCreateOrUpdateCmd extends InternationalizationCmd {
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
    private ContentType contentType;
    private List<UUID> personId;
    private List<UUID> tagIds;
    private List<UUID> childIds;
}
