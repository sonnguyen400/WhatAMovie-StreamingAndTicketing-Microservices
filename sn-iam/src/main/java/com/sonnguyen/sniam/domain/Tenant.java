package com.sonnguyen.sniam.domain;

import com.sonnguyen.common.model.domain.TenancyDomain;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Getter
public class Tenant extends TenancyDomain {
    private UUID id;
    private String name;
    private String url;
}
