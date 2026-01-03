package com.sonnguyen.sncatalogue.application.dto.request;

import com.sonnguyen.common.model.application.request.InternationalizationRequest;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
public class TagCreateOrUpdateRequest extends InternationalizationRequest {
    private String title;
    private List<MessageLocale> messageLocales;

    @Data
    @SuperBuilder
    public static class MessageLocale extends AbstractMessageLocale {
        private String title;

        @Override
        public Map<String, String> getMessages() {
            return Map.of("title", title);
        }
    }
}
