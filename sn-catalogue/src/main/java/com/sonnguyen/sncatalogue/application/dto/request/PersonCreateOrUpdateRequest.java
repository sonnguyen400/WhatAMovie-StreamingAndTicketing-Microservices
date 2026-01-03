package com.sonnguyen.sncatalogue.application.dto.request;

import com.sonnguyen.common.model.application.request.InternationalizationRequest;
import com.sonnguyen.sncatalogue.infrastructure.constant.PersonRole;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class PersonCreateOrUpdateRequest extends InternationalizationRequest {
    private String fullName;
    private LocalDate dateOfBirth;
    private String description;
    private Double height;
    private Double weight;
    private String education;
    private List<PersonRole> role;
    private List<MessageLocale> messageLocales;

    @Data
    public static class MessageLocale extends AbstractMessageLocale {
        private String fullName;
        private String description;
        private String education;

        @Override
        public Map<String, String> getMessages() {
            return Map.of("fullName", fullName, "description", description, "education", education);
        }
    }
}
