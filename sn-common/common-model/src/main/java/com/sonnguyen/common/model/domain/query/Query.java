package com.sonnguyen.common.model.domain.query;


import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class Query {
    private LocaleCode localeCode;

    public Query() {
    }

    public Query(LocaleCode localeCode) {
        this.localeCode = localeCode;
    }
}
