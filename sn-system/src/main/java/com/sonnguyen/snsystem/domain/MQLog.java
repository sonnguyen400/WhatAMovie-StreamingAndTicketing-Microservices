package com.sonnguyen.snsystem.domain;

import com.sonnguyen.common.model.infrastructure.support.enums.MQPlatform;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MQLog {
    private UUID id;                    // uuid
    private MQPlatform platform;      // KAFKA, RABBITMQ, ACTIVEMQ, SQS
    private AuditContext audit;
    private String direction;             // PRODUCE or CONSUME
    private String broker;                // kafka://host:9092
    private String topic;
    private Integer partition;
    private Long offset;
    private String messageId;             // id trong payload (nếu có)
    private String key;                   // kafka key
    private Long sizeBytes;
    private String status;                // PUBLISHED, DELIVERED, FAILED, RETRIED
    private Instant producedAt;
    private Instant consumedAt;
    private String consumerGroup;
    private String handler;
    private String errorStacktrace;
}
