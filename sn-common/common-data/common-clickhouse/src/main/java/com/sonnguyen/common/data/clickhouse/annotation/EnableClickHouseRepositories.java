package com.sonnguyen.common.data.clickhouse.annotation;

//import com.sonnguyen.common.data.clickhouse.ClickHouseRepositoriesRegistrar;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(ClickHouseRepositoriesRegistrar.class)
public @interface EnableClickHouseRepositories {
    String[] value() default {};
    String[] basePackages() default {};
    Class<?>[] basePackageClasses() default {};
}
