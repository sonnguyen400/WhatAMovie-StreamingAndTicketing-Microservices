package com.sonnguyen.common.util;

import com.sonnguyen.common.model.infrastructure.support.enums.Mimetype;

public class FileUtils {

    private FileUtils() {
    }

    /**
     * Extracts the extension from the original file name.
     *
     * @param originalFilename The original filename
     * @return The extension in lowercase, or empty string if no extension is found
     */
    public static String getExtension(String originalFilename) {
        if (originalFilename == null) {
            return null;
        }
        int lastIndexOfDot = originalFilename.lastIndexOf('.');
        if (lastIndexOfDot == -1 || lastIndexOfDot == originalFilename.length() - 1) {
            return "";
        }
        return originalFilename.substring(lastIndexOfDot + 1).toLowerCase();
    }

    /**
     * Gets the mime type for a given file extension.
     * Supported Mimetypes are aligned with com.sonnguyen.common.model.infrastructure.support.enums.Mimetype.
     *
     * @param extension The file extension
     * @return The corresponding mime type string
     */
    public static Mimetype getMimeType(String extension) {
        if (extension == null) {
            return Mimetype.APPLICATION_OCTET_STREAM;
        }
        switch (extension.toLowerCase()) {
            case "json":
                return Mimetype.APPLICATION_JSON;
            case "xml":
                return Mimetype.APPLICATION_XML;
            case "pdf":
                return Mimetype.APPLICATION_PDF;
            case "zip":
                return Mimetype.APPLICATION_ZIP;
            case "txt":
                return Mimetype.TEXT_PLAIN;
            case "html":
            case "htm":
                return Mimetype.TEXT_HTML;
            case "md":
            case "markdown":
                return Mimetype.TEXT_MARKDOWN;
            case "jpg":
            case "jpeg":
                return Mimetype.IMAGE_JPEG;
            case "png":
                return Mimetype.IMAGE_PNG;
            case "gif":
                return Mimetype.IMAGE_GIF;
            case "svg":
                return Mimetype.IMAGE_SVG_XML;
            case "mp3":
                return Mimetype.AUDIO_MPEG;
            case "mp4":
                return Mimetype.VIDEO_MP4;
            case "xls":
                return Mimetype.EXCEL;
            case "xlsx":
                return Mimetype.EXCEL_OPENXML;
            case "doc":
                return Mimetype.WORD;
            case "docx":
                return Mimetype.WORD_OPENXML;
            case "ppt":
                return Mimetype.POWERPOINT;
            case "pptx":
                return Mimetype.POWERPOINT_OPENXML;
            case "rtf":
                return Mimetype.RTF;
            case "csv":
                return Mimetype.CSV;
            case "odt":
                return Mimetype.ODT;
            case "ods":
                return Mimetype.ODS;
            case "odp":
                return Mimetype.ODP;
            case "form-data":
                return Mimetype.MULTIPART_FORM_DATA;
            default:
                return Mimetype.APPLICATION_OCTET_STREAM;
        }
    }
}
