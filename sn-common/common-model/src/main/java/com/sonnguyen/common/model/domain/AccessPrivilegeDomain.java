package com.sonnguyen.common.model.domain;

import com.sonnguyen.common.model.domain.command.AccessPrivilegeCreateCmd;
import com.sonnguyen.common.model.domain.command.AccessPrivilegeEvaluateCmd;
import com.sonnguyen.common.model.infrastructure.support.enums.DomainType;
import com.sonnguyen.common.model.infrastructure.support.enums.LocaleCode;
import com.sonnguyen.common.model.infrastructure.support.enums.PrivilegeScope;
import com.sonnguyen.common.model.infrastructure.support.enums.ResourceScope;
import com.sonnguyen.common.util.CollectionUtils;
import com.sonnguyen.common.util.DataUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public abstract class AccessPrivilegeDomain extends AuditingDomain {
    private UUID resourceId;
    private DomainType resourceType;
    private UUID accessDomainId; // DEVICE, MEMBER, MEMBER_GROUP
    private DomainType domainType; // User or Organization
    private LocaleCode locale;
    private TimePolicy timePolicy;
    private UUID deviceId;
    private Integer ageLimit;
    private PrivilegeScope scope; //Locale, Domain, Time, Nonnull
    private ResourceScope action; // Non null

    public AccessPrivilegeDomain(AccessPrivilegeCreateCmd cmd) {
        this.resourceId = cmd.getResourceId();
        this.resourceType = cmd.getResourceType();
        this.locale = cmd.getLocale();
        this.accessDomainId = cmd.getAccessDomainId();
        this.domainType = cmd.getDomainType();
        this.scope = cmd.getScope();
        this.action = cmd.getAction();
        this.timePolicy = this.createTimePolicy(cmd);
    }

    private TimePolicy createTimePolicy(AccessPrivilegeCreateCmd cmd) {
        return TimePolicy.builder()
                .startTime(cmd.getStartTime())
                .endTime(cmd.getEndTime())
                .dayOfWeeks(cmd.getDayOfWeeks())
                .dayOfMonths(cmd.getDayOfMonths())
                .adaptiveTimezone(Boolean.TRUE.equals(cmd.getAdaptiveTimezone()))
                .build();
    }


    public boolean evaluate(AccessPrivilegeEvaluateCmd cmd) {
        boolean isValid = true;
        if (Objects.nonNull(this.timePolicy) && PrivilegeScope.TIME.equals(this.scope)) {
            return this.evaluateTimePolicy(cmd.getZoneId());
        }
        if (Objects.nonNull(this.accessDomainId) && CollectionUtils.isNotEmpty(cmd.getDomainIds()) && PrivilegeScope.ACCESS_DOMAIN.equals(this.scope)) {
            return cmd.getDomainIds().contains(this.accessDomainId);
        }
        if (Objects.nonNull(this.locale) && PrivilegeScope.LOCALE.equals(this.scope)) {
            return  Objects.equals(cmd.getLocaleCode(), this.locale);
        }
        if(Objects.nonNull(this.ageLimit) && PrivilegeScope.AGE_LIMIT.equals(this.scope)){
            return Objects.equals(cmd.getAge(), this.ageLimit);
        }
        return isValid;
    }

    public boolean evaluateTimePolicy(ZoneId zoneId) {
        if (Objects.isNull(this.timePolicy)) return true;
        Boolean isValid = true;
        ZoneId zone = DataUtils.getOrDefault(zoneId, ZoneOffset.UTC);
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        LocalDateTime startTime = Optional.ofNullable(this.timePolicy.getStartTime())
                .map(it -> LocalDateTime.ofInstant(it, ZoneOffset.UTC))
                .orElse(null);
        LocalDateTime endTime = Optional.ofNullable(this.timePolicy.getEndTime())
                .map(it -> LocalDateTime.ofInstant(it, ZoneOffset.UTC))
                .orElse(null);
        if (Boolean.TRUE.equals(this.timePolicy.getAdaptiveTimezone())) {
            now = LocalDateTime.now(zone);
            startTime = Optional.ofNullable(this.timePolicy.getStartTime())
                    .map(it -> LocalDateTime.ofInstant(it, zone))
                    .orElse(null);
            endTime = Optional.ofNullable(this.timePolicy.getEndTime())
                    .map(it -> LocalDateTime.ofInstant(it, zone))
                    .orElse(null);
        }
        if (Objects.nonNull(startTime) && !now.isAfter(startTime)) {
            return false;
        }
        if (Objects.nonNull(endTime) && !now.isBefore(endTime)) {
            return false;
        }
        if (CollectionUtils.isNotEmpty(this.timePolicy.getDayOfWeeks())
                && !this.timePolicy.getDayOfWeeks().contains(now.getDayOfWeek().getValue())) {
            return false;
        }
        if (CollectionUtils.isNotEmpty(this.timePolicy.getDayOfMonths())
                && !this.timePolicy.getDayOfMonths().contains(now.getDayOfMonth())) {
            return false;
        }
        return isValid;
    }
}
