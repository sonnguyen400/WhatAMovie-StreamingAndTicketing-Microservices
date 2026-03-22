package com.sonnguyen.common.model.application.request;

import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class InternationalizationRequest extends Request{

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static abstract class AbstractMessageLocale {
        private LocaleCode localeCode;

        public abstract Map<String, String> getMessages();
    }


    public abstract List<? extends AbstractMessageLocale> getMessageLocales();
}
