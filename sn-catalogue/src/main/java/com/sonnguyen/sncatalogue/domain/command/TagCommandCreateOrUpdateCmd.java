package com.sonnguyen.sncatalogue.domain.command;

import com.sonnguyen.common.model.infrastructure.constant.LocaleCode;
import lombok.Data;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Data
public class TagCommandCreateOrUpdateCmd {
    private String title;
    private Map<LocaleCode, String> messageLocales;
}
