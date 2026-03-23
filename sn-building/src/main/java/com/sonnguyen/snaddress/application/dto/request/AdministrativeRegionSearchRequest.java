package com.sonnguyen.snaddress.application.dto.request;

import com.sonnguyen.common.model.application.request.PagingRequest;
import lombok.Data;

import java.util.UUID;

@Data
public class AdministrativeRegionSearchRequest extends PagingRequest {
    private UUID countryId;
    private UUID parentId;
    private String keyword;
    private String type;
    private Integer level;
}
