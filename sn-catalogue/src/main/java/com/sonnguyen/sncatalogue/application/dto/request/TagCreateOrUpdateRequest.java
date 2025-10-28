package com.sonnguyen.sncatalogue.application.dto.request;

import com.sonnguyen.common.model.application.request.Request;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;
import java.util.Map;

@Getter
@Setter
public class TagCreateOrUpdateRequest extends Request {
    private String title;

    private Map<Locale, String> messageLocales;
}
