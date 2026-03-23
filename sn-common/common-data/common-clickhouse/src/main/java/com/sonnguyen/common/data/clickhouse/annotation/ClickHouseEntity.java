package com.sonnguyen.common.data.clickhouse.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation để đánh dấu một class là Entity trong ClickHouse
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClickHouseEntity {

    /**
     * Tên bảng trong ClickHouse
     */
    String tableName();

    /**
     * Engine của bảng (mặc định là MergeTree)
     */
    String engine() default "MergeTree()";

    /**
     * Order by clause cho ClickHouse
     */
    String orderBy() default "";

    /**
     * Partition by clause cho ClickHouse
     */
    String partitionBy() default "";

    /**
     * Có tự động tạo bảng không
     */
    boolean autoCreate() default true;
}
