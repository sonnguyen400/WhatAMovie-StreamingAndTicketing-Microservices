package com.sonnguyen.snstorage.infrastructure.support.constant;

public enum ImageFormat {
    JPEG("image/jpeg"),
    PNG("image/png"),
    WEBP("image/webp"),
    GIF("image/gif"),
    TIFF("image/tiff");

    private final String mimeType;

    ImageFormat(String mimeType) {
        this.mimeType = mimeType;
    }

    public static ImageFormat fromExtension(String extension) {
        if (extension == null) return null;
        if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg")) return JPEG;
        if (extension.equalsIgnoreCase("png")) return PNG;
        if (extension.equalsIgnoreCase("webp")) return WEBP;
        if (extension.equalsIgnoreCase("gif")) return GIF;
        if (extension.equalsIgnoreCase("tiff") || extension.equalsIgnoreCase("tif")) return TIFF;
        return null;
    }

    public String getMimeType() {
        return mimeType;
    }
}
