package com.sonnguyen.snaddress.application.dto.request;

import com.sonnguyen.common.model.application.request.PagingRequest;
import lombok.Data;

@Data
public class CountrySearchRequest extends PagingRequest {
    private String keyword;
}
