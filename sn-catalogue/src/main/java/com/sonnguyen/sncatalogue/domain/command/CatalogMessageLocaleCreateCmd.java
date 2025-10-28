package com.sonnguyen.sncatalogue.domain.command;

import com.sonnguyen.common.model.infrastructure.constant.LocaleCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogMessageLocaleCreateCmd {
    private String title;
    private String description;
    private String slug;
    private LocaleCode localeCode;
}
