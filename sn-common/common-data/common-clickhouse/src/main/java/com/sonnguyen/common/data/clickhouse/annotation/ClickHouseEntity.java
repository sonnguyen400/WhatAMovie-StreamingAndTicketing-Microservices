package com.sonnguyen.common.data.clickhouse.annotation;

import jakarta.persistence.Entity;

import java.lang.annotation.*;

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
