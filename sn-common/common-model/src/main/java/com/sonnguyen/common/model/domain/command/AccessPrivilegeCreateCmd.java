package com.sonnguyen.common.model.domain.command;

import com.sonnguyen.common.model.infrastructure.constant.DomainType;
import com.sonnguyen.common.model.infrastructure.constant.LocaleCode;
import com.sonnguyen.common.model.infrastructure.constant.PrivilegeAction;
import com.sonnguyen.common.model.infrastructure.constant.PrivilegeScope;
import com.sonnguyen.common.model.infrastructure.constant.ResourceScope;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class AccessPrivilegeCreateCmd {
    private UUID resourceId;
    private DomainType resourceType;
    private LocaleCode locale;
    private UUID accessDomainId;
    private DomainType domainType;
    private PrivilegeScope scope;
    private ResourceScope action;
    private Instant startTime;
    private Instant endTime;
    private List<Integer> dayOfWeeks;
    private List<Integer> dayOfMonths;
    private Boolean adaptiveTimezone;
}
