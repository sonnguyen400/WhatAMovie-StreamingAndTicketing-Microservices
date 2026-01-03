package com.sonnguyen.common.model.infrastructure.support.enums;

public enum Mimetype {
    APPLICATION_JSON("application/json"),
    APPLICATION_XML("application/xml"),
    APPLICATION_PDF("application/pdf"),
    APPLICATION_ZIP("application/zip"),
    APPLICATION_OCTET_STREAM("application/octet-stream"),
    TEXT_PLAIN("text/plain"),
    TEXT_HTML("text/html"),
    TEXT_MARKDOWN("text/markdown"),
    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/png"),
    IMAGE_GIF("image/gif"),
    IMAGE_SVG_XML("image/svg+xml"),
    AUDIO_MPEG("audio/mpeg"),
    VIDEO_MP4("video/mp4"),
    MULTIPART_FORM_DATA("multipart/form-data"),
    EXCEL("application/vnd.ms-excel"),
    EXCEL_OPENXML("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    WORD("application/msword"),
    WORD_OPENXML("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    POWERPOINT("application/vnd.ms-powerpoint"),
    POWERPOINT_OPENXML("application/vnd.openxmlformats-officedocument.presentationml.presentation"),
    RTF("application/rtf"),
    CSV("text/csv"),
    ODT("application/vnd.oasis.opendocument.text"),
    ODS("application/vnd.oasis.opendocument.spreadsheet"),
    ODP("application/vnd.oasis.opendocument.presentation");

    private final String value;


    Mimetype(String value) {
        this.value = value;
    }

    public static Mimetype ofValue(String value) {
        for (Mimetype mimetype : Mimetype.values()) {
            if (mimetype.value.equalsIgnoreCase(value)) {
                return mimetype;
            }
        }
        return APPLICATION_OCTET_STREAM;
    }

    public String getValue() {
        return value;
    }
}
