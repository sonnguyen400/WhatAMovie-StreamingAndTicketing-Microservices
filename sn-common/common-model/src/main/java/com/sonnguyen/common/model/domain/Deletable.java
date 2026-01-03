package com.sonnguyen.common.model.domain;

public interface Deletable {
    void delete();

    default void unDelete() {
    }
}
