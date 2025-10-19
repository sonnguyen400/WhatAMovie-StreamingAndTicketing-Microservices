package com.sonnguyen.snpayment.domain;

import com.sonnguyen.common.model.domain.AuditingDomain;
import com.sonnguyen.common.util.IdUtils;
import com.sonnguyen.snpayment.domain.command.IdempotencyCreateCmd;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.UUID;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class IdempotencyKey extends AuditingDomain {
    private UUID id;
    private String key;
    private String ownerService;
    private Instant expiredAt;
    private String response;
    private Boolean deleted;

    public IdempotencyKey(IdempotencyCreateCmd cmd) {
        this.id = IdUtils.nextId();
        this.key = cmd.getIdempotencyKey();
        this.expiredAt = cmd.getExpiredAt();
    }
}
