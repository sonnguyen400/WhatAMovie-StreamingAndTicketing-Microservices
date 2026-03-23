package com.sonnguyen.sncatalogue.application.dto.request;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Duration;
import java.util.UUID;

@Data
@SuperBuilder
public class MovieCreateUpdateRequest extends CatalogItemCreateUpdateRequest {
    private Integer releaseYear;
    private Duration duration;
    private UUID posterFileId;
    private UUID backdropId;
    private UUID trainerId;
}
