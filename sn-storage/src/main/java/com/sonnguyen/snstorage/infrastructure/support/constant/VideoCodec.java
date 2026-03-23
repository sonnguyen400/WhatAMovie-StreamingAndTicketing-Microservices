package com.sonnguyen.snstorage.infrastructure.support.constant;

public enum VideoCodec {
    H264("H.264 / AVC"),
    H265("H.265 / HEVC"),
    VP9("VP9"),
    AV1("AV1");

    private final String description;

    VideoCodec(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}