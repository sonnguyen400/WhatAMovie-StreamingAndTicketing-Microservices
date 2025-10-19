package com.sonnguyen.common.util;


import org.springframework.data.util.StreamUtils;

import java.util.Collection;
import java.util.stream.Collectors;

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

    public static <E> Collection<E> toCollection(Iterable<E> elements) {
        Collection var10000;
        if (elements instanceof Collection<E> collection) {
            var10000 = collection;
        } else {
            var10000 = (Collection) StreamUtils.createStreamFromIterator(elements.iterator()).collect(Collectors.toList());
        }

        return var10000;
    }
}
