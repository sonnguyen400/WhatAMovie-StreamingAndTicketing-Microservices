package com.sonnguyen.common.data.clickhouse.annotation;

import java.lang.annotation.*;

/**
 * Annotation để đánh dấu field trong ClickHouse Entity
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClickHouseField {
    
    /**
     * Tên cột trong ClickHouse (mặc định là tên field)
     */
    String name() default "";
    
    /**
     * Kiểu dữ liệu trong ClickHouse
     */
    String type() default "";
    
    /**
     * Có được phép null không
     */
    boolean nullable() default true;
    
    /**
     * Giá trị mặc định
     */
    String defaultValue() default "";
    
    /**
     * Field có được include trong query không
     */
    boolean includeInQuery() default true;


    boolean readOnly() default false;
}
