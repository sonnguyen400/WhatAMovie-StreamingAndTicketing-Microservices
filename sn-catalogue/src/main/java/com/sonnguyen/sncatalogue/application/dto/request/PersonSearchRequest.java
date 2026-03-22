package com.sonnguyen.sncatalogue.application.dto.request;

import com.sonnguyen.common.model.application.request.PagingRequest;
import com.sonnguyen.sncatalogue.infrastructure.constant.PersonRole;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PersonSearchRequest extends PagingRequest {
    private String keyword;
    private PersonRole role;
    private UUID catalogId;
}
