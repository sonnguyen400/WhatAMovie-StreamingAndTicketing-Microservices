package com.sonnguyen.snpayment.domain.command;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class IdempotencyCreateCmd {
    private String idempotencyKey;
    private Instant expiredAt;
}
