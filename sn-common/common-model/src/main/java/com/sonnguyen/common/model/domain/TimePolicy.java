package com.sonnguyen.common.model.domain;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.util.List;

@Value
@Builder
public class TimePolicy {
    Instant startTime;
    Instant endTime;
    List<Integer> dayOfWeeks;
    List<Integer> dayOfMonths;
    Boolean adaptiveTimezone;
}
