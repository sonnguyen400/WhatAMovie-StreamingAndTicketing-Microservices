package com.sonnguyen.snultility.application.dto.request;

import com.sonnguyen.common.model.application.request.PagingRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class TagSearchRequest extends PagingRequest {
    private String keyword;
    private Boolean enrichLocale;
}
