package com.sonnguyen.sncatalogue.application.dto.response;

import java.util.UUID;

public record TagListResponse(
        UUID id,
        String title
) {
}
