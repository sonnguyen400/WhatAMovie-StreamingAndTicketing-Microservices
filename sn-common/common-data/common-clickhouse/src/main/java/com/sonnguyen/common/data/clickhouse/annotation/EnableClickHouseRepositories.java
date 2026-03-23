package com.sonnguyen.common.data.clickhouse.annotation;

//import com.sonnguyen.common.data.clickhouse.ClickHouseRepositoriesRegistrar;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(ClickHouseRepositoriesRegistrar.class)
public @interface EnableClickHouseRepositories {
    String[] value() default {};

    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};
}
