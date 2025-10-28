package com.sonnguyen.common.model.application.request;

import com.sonnguyen.common.model.infrastructure.constant.LocaleCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@NoArgsConstructor
public abstract class Request implements Serializable {
    private LocaleCode localeCode;
}
