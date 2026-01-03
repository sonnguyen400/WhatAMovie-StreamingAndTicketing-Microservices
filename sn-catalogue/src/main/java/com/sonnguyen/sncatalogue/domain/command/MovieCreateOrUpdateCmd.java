package com.sonnguyen.sncatalogue.domain.command;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Duration;
import java.util.UUID;

@Data
@SuperBuilder
public class MovieCreateOrUpdateCmd extends CatalogItemCreateOrUpdateCmd{
    private Integer releaseYear;
    private Duration duration;
    private UUID posterFileId;
    private UUID backdropId;
    private UUID trainerId;
}
