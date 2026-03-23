package com.sonnguyen.sncatalogue.application.dto.request;

import com.sonnguyen.common.model.application.request.InternationalizationRequest;
import com.sonnguyen.sncatalogue.infrastructure.constant.ContentStatus;
import com.sonnguyen.sncatalogue.infrastructure.constant.ContentType;
import com.sonnguyen.sncatalogue.infrastructure.constant.DistributionChannel;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@SuperBuilder
public class CatalogItemCreateUpdateRequest extends InternationalizationRequest {
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
    private List<MessageLocale> messageLocales;
    private List<UUID> personId;
    private List<UUID> tagIds;
    private List<UUID> childIds;

    @Data
    public static class MessageLocale extends AbstractMessageLocale {
        private String title;
        private String description;
        private String slug;
        private String synopsis;

        @Override
        public Map<String, String> getMessages() {
            return Map.of("title", title, "description", description, "slug", slug, "synopsis", synopsis);
        }
    }
}
