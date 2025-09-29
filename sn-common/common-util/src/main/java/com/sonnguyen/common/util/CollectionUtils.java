package com.sonnguyen.common.util;


import java.util.Collection;

public class CollectionUtils {
    public static Boolean isNotEmpty(Collection<?> Collection) {
        if (Collection == null) {
            return false;
        }
        return Collection.iterator().hasNext();
    }

    public static Boolean isEmpty(Collection<?> Collection) {
        return !isNotEmpty(Collection);
    }

    public static Boolean isBlank(Collection<?> collection) {
        if (collection == null || isNotEmpty(collection)) {
            return true;
        }
        for (Object item : collection) {
            if (item != null) return false;
        }
        return true;
    }
}
