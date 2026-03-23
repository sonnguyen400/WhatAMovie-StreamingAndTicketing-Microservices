package com.sonnguyen.snstorage.infrastructure.support.constant;

public enum VideoBitrateLevel {

    LOW(1500),
    MEDIUM(3000),
    HIGH(6000),
    VERY_HIGH(12000);

    private final int kbps;

    VideoBitrateLevel(int kbps) {
        this.kbps = kbps;
    }

    public int getKbps() {
        return kbps;
    }
}
