package com.sonnguyen.common.util;

public class DataUtils {
    public static <T> T getOrDefault(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    public static Void empty() {
        return null;
    }
}
