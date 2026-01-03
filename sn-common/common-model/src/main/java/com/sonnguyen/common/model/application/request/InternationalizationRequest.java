package com.sonnguyen.common.model.application.request;

import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public abstract class InternationalizationRequest {

    @Data
    public static abstract class AbstractMessageLocale {
        private LocaleCode localeCode;

        public abstract Map<String, String> getMessages();
    }


    public abstract List<? extends AbstractMessageLocale> getMessageLocales();
}
