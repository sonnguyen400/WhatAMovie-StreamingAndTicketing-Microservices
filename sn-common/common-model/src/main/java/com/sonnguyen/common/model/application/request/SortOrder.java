package com.sonnguyen.common.model.application.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortOrder {
    private String field;
    private Direction sort;

    public static enum Direction {
        ASC,
        DESC
    }
}
