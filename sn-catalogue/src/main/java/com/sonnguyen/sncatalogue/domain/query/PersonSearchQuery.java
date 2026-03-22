package com.sonnguyen.sncatalogue.domain.query;

import com.sonnguyen.common.model.domain.PagingSearchQuery;
import com.sonnguyen.sncatalogue.infrastructure.constant.PersonRole;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class PersonSearchQuery extends PagingSearchQuery {
    private String keyword;
    private PersonRole role;
    private UUID catalogId;
}
