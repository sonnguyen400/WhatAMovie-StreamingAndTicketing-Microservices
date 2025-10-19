package com.sonnguyen.snnotification.domain.repository;

import com.sonnguyen.common.data.core.DomainRepository;
import com.sonnguyen.snnotification.domain.Notification;

import java.util.UUID;

public interface NotificationRepository extends DomainRepository<Notification, UUID> {
}
