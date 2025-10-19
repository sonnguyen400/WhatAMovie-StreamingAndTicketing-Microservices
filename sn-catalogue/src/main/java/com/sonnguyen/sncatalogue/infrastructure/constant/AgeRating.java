package com.sonnguyen.sncatalogue.infrastructure.constant;

public enum AgeRating {
    G("General Audiences"),
    PG("Parental Guidance"),
    PG13("Parents Strongly Cautioned"),
    R("Restricted"),
    NC17("Adults Only"),
    UNRATED("Unrated");

    private final String description;

    AgeRating(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
