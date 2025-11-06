package com.sonnguyen.common.model.domain.command;

import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import com.sonnguyen.common.model.infrastructure.support.enums.PrivilegeScope;
import lombok.Builder;
import lombok.Data;

import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class AccessPrivilegeEvaluateCmd {
    private LocaleCode localeCode;
    private List<UUID> domainIds;
    private DomainType domainType;
    private PrivilegeScope scope;
    private Integer age;
    @Builder.Default
    private ZoneId zoneId = ZoneId.systemDefault();
}
