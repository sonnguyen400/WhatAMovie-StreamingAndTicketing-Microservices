package com.sonnguyen.sncatalogue.domain;

import lombok.Builder;
import lombok.Value;

import java.time.Duration;
import java.util.UUID;

@Value
@Builder
public class CatalogMetadata {
    Integer releaseYear;
    Duration duration; // minutes, null for series
    String synopsis;
    UUID posterFileId;
    UUID backdropId;
    UUID trainerId;

    public boolean isSeries() {
        return duration == null;
    }
}
