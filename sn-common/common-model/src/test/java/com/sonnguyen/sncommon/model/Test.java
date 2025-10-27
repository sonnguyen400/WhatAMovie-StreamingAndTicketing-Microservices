package com.sonnguyen.sncommon.model;

import com.sonnguyen.common.model.domain.AccessPrivilegeDomain;
import com.sonnguyen.common.model.domain.command.AccessPrivilegeCreateCmd;
import com.sonnguyen.common.model.infrastructure.constant.PrivilegeScope;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        AccessPrivilegeCreateCmd privilegeCreateCmd = AccessPrivilegeCreateCmd.builder()
//                .endTime(Instant.now().plusSeconds(3600))
//                .startTime(Instant.now())
//                .adaptiveTimezone(true)
//                .scope(PrivilegeScope.TIME)
//                .build();
//        AccessPrivilegeDomain accessPrivilegeDomain = new AccessPrivilegeDomain(privilegeCreateCmd);
//        boolean test1 = accessPrivilegeDomain.evaluateTimePolicy(ZoneId.of("UTC-6"));
//        AccessPrivilegeCreateCmd privilegeCreateCmd2 = AccessPrivilegeCreateCmd.builder()
//                .adaptiveTimezone(true)
//                .scope(PrivilegeScope.TIME)
//                .dayOfMonths(List.of(22))
//                .build();
//        AccessPrivilegeDomain accessPrivilegeDomain2 = new AccessPrivilegeDomain(privilegeCreateCmd2);
//        boolean test2= accessPrivilegeDomain2.evaluateTimePolicy(ZoneId.of("UTC+3"));
    }
}
