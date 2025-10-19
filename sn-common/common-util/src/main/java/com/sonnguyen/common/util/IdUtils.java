package com.sonnguyen.common.util;

import java.util.Optional;
import java.util.UUID;

public class IdUtils {
    public static UUID nextId() {
        return UUID.randomUUID();
    }

    public static String nextStrId() {
        return UUID.randomUUID().toString();
    }

    public Optional<UUID> parseId(String id) {
        try {
            UUID uuid = UUID.fromString(id);
            return Optional.of(uuid);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
