package com.sonnguyen.common.model.application.request;

import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@NoArgsConstructor
public abstract class Request implements Serializable {
    private LocaleCode localeCode;

    public LocaleCode getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(LocaleCode localeCode) {
        this.localeCode = localeCode;
    }
}
