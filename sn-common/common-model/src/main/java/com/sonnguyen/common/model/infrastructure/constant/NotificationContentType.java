package com.sonnguyen.common.model.infrastructure.constant;

public enum NotificationContentType {
    HTML,
    TEXT,
    MARKDOWN,
    JSON,
    XML;

    public Mimetype toMimeType() {
        return switch (this) {
            case HTML -> Mimetype.TEXT_HTML;
            case MARKDOWN -> Mimetype.TEXT_MARKDOWN;
            case JSON -> Mimetype.APPLICATION_JSON;
            case XML -> Mimetype.APPLICATION_XML;
            default -> Mimetype.TEXT_PLAIN;
        };
    }
}
