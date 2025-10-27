package com.sonnguyen.common.model.domain;

import com.sonnguyen.common.model.domain.command.AccessPrivilegeEvaluateCmd;
import com.sonnguyen.common.model.infrastructure.constant.ResourceScope;
import com.sonnguyen.common.util.CollectionUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public abstract class PolicyResourceDomain<T extends AccessPrivilegeDomain> extends AuditingDomain {
    private List<T> policies;

    private void enrichPolicy(List<T> policies) {
        this.policies = policies;
    }

    public void addAccess(T t) {
        if (Objects.isNull(this.policies)) {
            this.policies = new ArrayList<>();
        }
        this.policies.add(t);
    }

    public abstract void updatePolicies(List<T> t);

    public <E extends AccessPrivilegeEvaluateCmd> List<ResourceScope> validatePolicies(E e) {
        if (CollectionUtils.isEmpty(this.policies)) return List.of(ResourceScope.values());
        ArrayList<T> policies = new ArrayList<>(this.policies);
        if (policies.stream().anyMatch(it -> Objects.nonNull(it.getAccessDomainId())) && CollectionUtils.isNotEmpty(e.getDomainIds())) {
            policies = policies.stream()
                    .filter(it -> Objects.isNull(it.getAccessDomainId()) || e.getDomainIds().contains(it.getAccessDomainId()))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        if (policies.stream().map(it -> it.evaluate(e)).anyMatch(it -> !it)) {
            return new ArrayList<>();
        } else {
            Set<ResourceScope> actionSet = policies.stream().map(AccessPrivilegeDomain::getAction)
                    .collect(Collectors.toSet());
            if (actionSet.contains(ResourceScope.FULL)) return List.of(ResourceScope.values());
            return actionSet.stream().toList();
        }
    }
}
