package com.sonnguyen.sncatalogue.application.dto.request;

import com.sonnguyen.common.model.infrastructure.constant.LocaleCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogMessageLocaleCreateRequest {
    private String title;
    private String description;
    private String slug;
    private LocaleCode localeCode;
}
