package com.sonnguyen.sncatalogue.application.dto.response;

import java.util.UUID;

public record ShortTagDTO(
        UUID id,
        String title
) {
}
