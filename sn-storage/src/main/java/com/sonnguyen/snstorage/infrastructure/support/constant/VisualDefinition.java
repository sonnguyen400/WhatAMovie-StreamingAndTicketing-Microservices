package com.sonnguyen.snstorage.infrastructure.support.constant;

import java.util.List;

public enum VisualDefinition {

    SD_480P("480p", 854, 480, 1),
    HD_720P("720p", 1280, 720, 1.5),
    FULL_HD_1080P("1080p", 1920, 1080, 2.25),
    QHD_1440P("1440p", 2560, 1440, 3),
    UHD_4K("4K", 3840, 2160, 4.5),
    UHD_8K("8K", 7680, 4320, 9);

    private final String name;
    private final int width;
    private final int height;
    private final double weight;

    VisualDefinition(String name, int width, int height, double weight) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    public static List<VisualDefinition> getLowerResolutions(VisualDefinition resolution) {
        return List.of(values()).stream()
                .filter(v -> v.weight < resolution.weight)
                .toList();
    }

    public static VisualDefinition closest(int width, int height) {
        VisualDefinition closest = null;
        int minDiff = Integer.MAX_VALUE;

        for (VisualDefinition v : VisualDefinition.values()) {
            int diff = Math.abs(v.getWidth() - width)
                    + Math.abs(v.getHeight() - height);

            if (diff < minDiff) {
                minDiff = diff;
                closest = v;
            }
        }
        return closest;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getResolution() {
        return width + "x" + height;
    }
}
