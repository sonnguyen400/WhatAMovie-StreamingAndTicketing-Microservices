package com.sonnguyen.sncatalogue.domain;

import lombok.Value;

import java.time.Duration;
import java.util.List;

@Value
public class CatalogMetadata {
    Integer releaseYear;
    Duration duration; // minutes, null for series
    String synopsis;
    String posterUrl;
    String backdropUrl;
    String trailerUrl; // nullable
    List<String> productionCountries;
    List<String> originalAudioLanguages;
    List<String> subtitleLanguages;

    public boolean isSeries() {
        return duration == null;
    }
}
